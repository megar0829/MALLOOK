package io.ssafy.mallook.global.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Log4j2
@RequiredArgsConstructor
@Component
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;
    @Value("${spring.server.role}")
    private String serverRole;

    @Scheduled(cron = "0 0 0 * * 1")
    @SchedulerLock(name = "couponTask", lockAtLeastFor = "50s", lockAtMostFor = "10m")
    public void runJob() {
        String time = LocalDateTime.now().toString();
        if (!(serverRole.equals("batch"))) {
            log.info("현재 서버 역할이 '{}'로 설정되어 있어, 'gradeJob' 작업을 수행하지 않습니다.", serverRole);
            return;
        }

        try {
            Job job = jobRegistry.getJob("gradeJob");
            JobParametersBuilder jobParameter = new JobParametersBuilder().addString("time", time);
            jobLauncher.run(job, jobParameter.toJobParameters());
        } catch (NoSuchJobException | JobRestartException | JobParametersInvalidException |
                 JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException e) {
            throw new RuntimeException(e);
        }
    }
}
