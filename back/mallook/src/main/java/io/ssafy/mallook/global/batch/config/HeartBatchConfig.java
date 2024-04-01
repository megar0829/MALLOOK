package io.ssafy.mallook.global.batch.config;

import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.entity.Script;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.entity.Style;
import io.ssafy.mallook.global.batch.dao.Top50RedisDao;
import io.ssafy.mallook.global.batch.dto.TopScriptDto;
import io.ssafy.mallook.global.batch.dto.TopStyleDto;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class HeartBatchConfig {

    private final String JOB_NAME = "heartInitJob";
    private final int CHUNK_SIZE = 1000;
    private final StyleRepository styleRepository;
    private final ScriptRepository scriptRepository;
    private final Top50RedisDao top50RedisDao;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job heartInitJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new JobBuilder("heartInitJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(this.changeTopScriptStep(jobRepository, transactionManager))
                .next(this.resetScriptTotalLikeStep(jobRepository, transactionManager))
                .next(this.changeTopStyleStep(jobRepository, transactionManager))
                .next(this.resetStyleTotalLikeStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step changeTopScriptStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("changeScriptStep", jobRepository)
                .tasklet(this.changeScriptTask(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet changeScriptTask() {
        return (contribution, chunkContext) -> {
            List<Long> topScriptIdList = scriptRepository.findTop50ScriptsOrderByTotalLikeDesc()
                    .stream()
                    .map(Script::getId)
                    .toList();
            TopScriptDto scriptDto = TopScriptDto.builder()
                    .scriptIdList(topScriptIdList)
                    .build();
            top50RedisDao.saveScripts(scriptDto, 35);

            log.info("스크립트 초기화 작업 완료");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step changeTopStyleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("changeStyleStep", jobRepository)
                .tasklet(this.changeStyleTask(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet changeStyleTask() {
        return (contribution, chunkContext) -> {
            List<Long> topStyleIdList = styleRepository.findTop50StylesOrderByTotalLikeDesc()
                    .stream()
                    .map(Style::getId).toList();
            TopStyleDto styleDto = TopStyleDto.builder()
                    .styleIdList(topStyleIdList)
                    .build();
            top50RedisDao.saveStyles(styleDto, 35);

            log.info("스타일 초기화 작업 완료");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step resetScriptTotalLikeStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new StepBuilder("resetScriptTotalLikeStep", jobRepository)
                .<Script, Script>chunk(CHUNK_SIZE, transactionManager)
                .reader(loadScriptData())
                .processor(scriptProcessor())
                .writer(scriptWriter())
                .build();
    }

    @Bean
    public ItemReader<? extends Script> loadScriptData() throws Exception {
        JpaPagingItemReader<Script> jpaPagingItemReader = new JpaPagingItemReaderBuilder<Script>()
                .name(JOB_NAME + "_loadScriptData")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(CHUNK_SIZE)
                .queryString("select s from Script s")
                .build();
        jpaPagingItemReader.afterPropertiesSet();
        return jpaPagingItemReader;
    }

    @Bean
    public ItemProcessor<? super Script,? extends Script> scriptProcessor() {
        return script -> {
            script.resetTotalLike();
            return script;
        };
    }

    @Bean
    public ItemWriter<? super Script> scriptWriter() {
        return script -> script.forEach(log::info);
    }

    @Bean
    public Step resetStyleTotalLikeStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new StepBuilder("resetStyleTotalLikeStep", jobRepository)
                .<Style, Style>chunk(CHUNK_SIZE, transactionManager)
                .reader(loadStyleData())
                .processor(styleProcessor())
                .writer(styleWriter())
                .build();
    }

    private ItemReader<? extends Style> loadStyleData() throws Exception {
        JpaPagingItemReader<Style> jpaPagingItemReader = new JpaPagingItemReaderBuilder<Style>()
                .name(JOB_NAME + "_loadScriptData")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(CHUNK_SIZE)
                .queryString("select s from Style s")
                .build();
        jpaPagingItemReader.afterPropertiesSet();
        return jpaPagingItemReader;
    }

    private ItemProcessor<? super Style,? extends Style> styleProcessor() {
        return style -> {
            style.resetTotalLike();
            return style;
        };
    }

    private ItemWriter<? super Style> styleWriter() {
        return style -> style.forEach(log::info);
    }
}
