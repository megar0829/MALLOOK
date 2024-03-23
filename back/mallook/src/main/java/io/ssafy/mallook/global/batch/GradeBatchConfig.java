package io.ssafy.mallook.global.batch;

import io.ssafy.mallook.domain.grade.dao.GradeRepository;
import io.ssafy.mallook.domain.grade.entity.Level;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.util.Collections;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class GradeBatchConfig {
    private final int CHUNK_SIZE = 1000;
    private final String JOB_NAME = "memberJob";
    private final EntityManagerFactory entityManagerFactory;
    private final MemberRepository memberRepository;
    private final GradeRepository gradeRepository;
    @Bean
    public Job gradeJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new JobBuilder("gradeJob", jobRepository)
                .start(this.updateExpStep(jobRepository, transactionManager))
                .next(this.updateGradeStep(jobRepository, transactionManager))
                // todo: 쿠폰 발급 추가
                .build();
    }
    // 구매내역 조회 후 exp 갱신
    @Bean(JOB_NAME + "_updateExpStep")
    public Step updateExpStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new StepBuilder("updateExpStep", jobRepository)
                .<MemberExpDto, MemberExpDto>chunk(CHUNK_SIZE, transactionManager)
                .reader(loadRecentOrderData())
                .writer(changeMemberExpData())
                .build();
    }
    // exp에 따른 등급 갱신
    @Bean(JOB_NAME + "_updateGradeStep")
    public Step updateGradeStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new StepBuilder("updateGradeStep", jobRepository)
                .<Member, Member>chunk(CHUNK_SIZE, transactionManager)
                .reader(loadMemberData())
                .processor(checkMemberData())
                .writer(changeMemberGradeData())
                .build();
    }
    private ItemReader<? extends Member> loadMemberData() throws Exception {
        JpaPagingItemReader<Member> jpaPagingItemReader = new JpaPagingItemReaderBuilder<Member>()
                .name(JOB_NAME + "_loadUserData")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(CHUNK_SIZE)
                .queryString("select m from Member m")
                .build();
        jpaPagingItemReader.afterPropertiesSet();
        return jpaPagingItemReader;

    }
    private ItemReader<? extends MemberExpDto> loadRecentOrderData() throws Exception {
        JpaPagingItemReader<MemberExpDto> jpaPagingItemReader = new JpaPagingItemReaderBuilder<MemberExpDto>()
                .name(JOB_NAME + "_loadUserData")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(CHUNK_SIZE)
                .queryString("""
                        select new io.ssafy.mallook.global.batch.MemberExpDto(m, sum(o.totalPrice))
                        from Member m
                        join Orders o on m.id = o.member.id
                        where o.createdAt > :createdAt
                        group by m
                        """)
                .parameterValues(Collections.singletonMap("createdAt", LocalDateTime.now().minusMonths(6)))
                .build();
        jpaPagingItemReader.afterPropertiesSet();
        return jpaPagingItemReader;

    }
    private ItemProcessor<? super Member, ? extends Member> checkMemberData () {
        return member -> {
            if (member.availableLevelUp()) {
                return member;
            }
            return null;
        };
    }
    private ItemWriter<MemberExpDto> changeMemberExpData() {
        return members -> members.forEach( member -> {
            var newExp = member.calculateExp();
            member.member().setExp(newExp);
            memberRepository.save(member.member());
        });
    }
    private ItemWriter<? super Member> changeMemberGradeData() {
        return members -> members.forEach( member -> {
            var grade = gradeRepository.findByMember(member)
                    .orElseThrow(()-> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
            grade.setLevel(Level.getNextGrade(member.getExp()));
            gradeRepository.save(grade);
        });
    }
    // 등급별 쿠폰 제공
//    @Bean
//    public Step giveUserCouponStep(){
//        return null;
//    }


}
