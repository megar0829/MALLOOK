package io.ssafy.mallook.global.batch;

import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.orders.entity.Orders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class GradeBatchConfig {
    @Bean
    public Job gradeJob() {
        return new JobBuilder("gradeJob")
                .start(this.updatePurchaseStep())
                .next(this.updateUserGradeStep())
                .build();
    }
    // 구매내역 조횧 ㅜ exp 갱신
    @Bean
    public Step updatePurchaseStep() {
        return new StepBuilder("updatePurchaseStep")
                .build();
    }

    // exp로 level 갱신
    @Bean
    public Step updateUserGradeStep(){
        return new StepBuilder("updateUserGradeStep")
                .build();
    }

    private ItemReader<? extends Orders> loadUserData() {
        JpaPagingItemReader<Orders>
    }

}
