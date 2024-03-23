package io.ssafy.mallook.global.batch;

import io.ssafy.mallook.domain.coupon.dao.CouponRepository;
import io.ssafy.mallook.domain.coupon.entity.Coupon;
import io.ssafy.mallook.domain.coupon.entity.CouponType;
import io.ssafy.mallook.domain.grade.dao.GradeRepository;
import io.ssafy.mallook.domain.grade.entity.Grade;
import io.ssafy.mallook.domain.grade.entity.Level;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.member_coupon.dao.MemberCouponRepository;
import io.ssafy.mallook.domain.member_coupon.entity.MemberCoupon;
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

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class GradeBatchConfig {
    private final int CHUNK_SIZE = 1000;
    private final String JOB_NAME = "memberJob";
    private final EntityManagerFactory entityManagerFactory;
    private final MemberRepository memberRepository;
    private final GradeRepository gradeRepository;
    private final CouponRepository couponRepository;
    private final MemberCouponRepository memberCouponRepository;
    @Bean
    public Job gradeJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new JobBuilder("gradeJob", jobRepository)
                .start(this.updateExpStep(jobRepository, transactionManager))
                .next(this.updateGradeStep(jobRepository, transactionManager))
                // todo: 등급별 쿠폰 등록 및 회원별 쿠폰 등록
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
                .processor(checkMemberGradeData())
                .writer(changeMemberGradeData())
                .build();
    }
//    @Bean(JOB_NAME + "_updateGradeCouponStep")
//    public Step updateGradeCouponStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
//        return new StepBuilder("updateGradeCouponStep", jobRepository)
//                .<Member, Member>chunk(CHUNK_SIZE, transactionManager)
//                .reader(loadMemberData())
//                .writer(changeMemberGradeData())
//                .build();
//    }
    private ItemReader<? extends Member> loadMemberData() throws Exception {
        JpaPagingItemReader<Member> jpaPagingItemReader = new JpaPagingItemReaderBuilder<Member>()
                .name(JOB_NAME + "_loadMemberData")
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
    private ItemProcessor<? super Member, ? extends Member> checkMemberGradeData () {
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
//    private ItemWriter<? super Member> createCouponAndMemberCoupon() {
//        // 등급별 쿠폰 등록\
//        Arrays.stream(Level.values()).forEach(level -> {
//            // level 별 쿠폰 발급
//            couponRepository.save(Coupon.builder()
//                        .name("회원 등급별 정기 쿠폰")
//                        .type(CouponType.RATIO)
//                        .amount(level.discountRate.toString())
//                        .expiredTime(LocalDateTime.now().plusDays(5))
//                        .build());
//        });
//        // 회원별 쿠폰 등록
//        return members -> members.forEach( member -> {
//            couponRepository.;
//            memberCouponRepository.save(
//                    MemberCoupon.builder()
//                            .coupon()
//                            .build()
//            );
//
//        });
//    }
}
