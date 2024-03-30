package io.ssafy.mallook.global.batch.config;

import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.entity.Script;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.entity.Style;
import io.ssafy.mallook.global.batch.dao.Top50RedisDao;
import io.ssafy.mallook.global.batch.dto.TopScriptDto;
import io.ssafy.mallook.global.batch.dto.TopStyleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class HeartBatchConfig {

    private final StyleRepository styleRepository;
    private final ScriptRepository scriptRepository;
    private final Top50RedisDao top50RedisDao;

    @Bean
    public Job getHeartInitJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("heartInitJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(changeTopScript(jobRepository, transactionManager))
                .next(changeTopStyle(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step changeTopScript(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("changeScriptStep", jobRepository)
                .tasklet(changeScriptTask(), transactionManager)
                .build();
    }

    @Bean
    public Step changeTopStyle(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("changeStyleStep", jobRepository)
                .tasklet(changeStyleTask(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet changeScriptTask() {
        return (contribution, chunkContext) -> {
            List<Script> topScript = scriptRepository.findTop50ScriptsOrderByTotalLikeDesc();
            TopScriptDto scriptDto = TopScriptDto.builder()
                    .scripts(topScript)
                    .build();
            top50RedisDao.saveScripts(scriptDto, 35);

            log.info("스크립트 초기화 작업 완료");
            return RepeatStatus.FINISHED;
        };
    }

    private Tasklet changeStyleTask() {
        return (contribution, chunkContext) -> {
            List<Style> topStyle = styleRepository.findTop50StylesOrderByTotalLikeDesc();
            TopStyleDto styleDto = TopStyleDto.builder()
                    .styles(topStyle)
                    .build();
            top50RedisDao.saveStyles(styleDto, 35);

            log.info("스크립트 초기화 작업 완료");
            return RepeatStatus.FINISHED;
        };
    }
}
