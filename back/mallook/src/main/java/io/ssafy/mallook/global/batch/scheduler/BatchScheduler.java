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

    @Value("${server.role}")
    private String serverRole;
    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;

//    @Scheduled(cron = "* * * * * *")
    @Scheduled(cron = "0 0 0 * * 1")
    @SchedulerLock(name = "couponSchedule", lockAtLeastFor = "50s", lockAtMostFor = "10m")
    public void runJob() {
        if (!"batch".equals(serverRole)) {
            return; // 서버 역할이 batch가 아니면 작업을 실행하지 않음
        }

        String time = LocalDateTime.now().toString();

        try {
            Job job = jobRegistry.getJob("gradeJob");
            JobParametersBuilder jobParameter = new JobParametersBuilder().addString("time", time);
            jobLauncher.run(job, jobParameter.toJobParameters());
        } catch (NoSuchJobException | JobRestartException | JobParametersInvalidException |
                 JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException e) {
            throw new RuntimeException(e);
        }
    }

//    @Scheduled(cron = "* * * * * *")
    @Scheduled(cron = "0 0/30 * * * *") // 매 30분마다 실행
    @SchedulerLock(name = "heartSchedule", lockAtLeastFor = "50s", lockAtMostFor = "10m")
    public void runSecondJob() {
        if (!"batch".equals(serverRole)) {
            return; // 서버 역할이 batch가 아니면 작업을 실행하지 않음
        }

        String time = LocalDateTime.now().toString();

        try {
            Job job = jobRegistry.getJob("heartInitJob");
            JobParametersBuilder jobParameter = new JobParametersBuilder().addString("time", time);
            jobLauncher.run(job, jobParameter.toJobParameters());
        } catch (NoSuchJobException | JobRestartException | JobParametersInvalidException |
                 JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException e) {
            throw new RuntimeException(e);
        }
    }
}
