package io.ssafy.mallook.global.batch.config;

import io.ssafy.mallook.domain.heart.script_heart.dao.ScriptHeartRepository;
import io.ssafy.mallook.domain.heart.style_heart.dao.StyleHeartRepository;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.entity.Script;
import io.ssafy.mallook.global.batch.dao.Top50RedisDao;
import io.ssafy.mallook.global.batch.dto.TopScriptDto;
import io.ssafy.mallook.global.batch.tasklet.UpdateRedisValueTasklet;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class HeartBatchConfig {

    private final int CHUNK_SIZE = 1000;
    private final String JOB_NAME = "memberJob";
    private final Top50RedisDao top50RedisDao;
    private final EntityManagerFactory entityManagerFactory;
    private final ScriptRepository scriptRepository;
    private final StyleHeartRepository styleHeartRepository;
    private final UpdateRedisValueTasklet updateRedisValueTasklet;

    @Bean
    public Job getHeartInitJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("heartInitJob", jobRepository)
                .start(changeTopScript())
    }

    @Bean
    public Step changeTopScript(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        List<Script> topScript = scriptRepository.findTop50ScriptsOrderByTotalLikeDesc();
        TopScriptDto scriptDto = TopScriptDto.builder()
                .scripts(topScript)
                .build();
        top50RedisDao.saveScripts(scriptDto, 35);
        return new StepBuilder(jobRepository, transactionManager)
                .tasklet(updateRedisValueTasklet)
                .build();
    }
}
