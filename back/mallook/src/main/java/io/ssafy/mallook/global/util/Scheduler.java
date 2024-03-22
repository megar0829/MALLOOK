package io.ssafy.mallook.global.util;

import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@EnableScheduling
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Scheduler {

    private final ScriptRepository scriptRepository;
    private final StyleRepository styleRepository;

    @Transactional
    @Scheduled(cron = "0 * * * * *")
    @SchedulerLock(name = "resetScriptLikeTask", lockAtLeastFor = "50s", lockAtMostFor = "10m")
    public void resetScriptLike() {
        log.info("작업 실행");
        scriptRepository.resetAllTotalLike();
        log.info("스크립트 좋아요 reset");
    }

    @Transactional
    @Scheduled(cron = "0 * * * * *")
    @SchedulerLock(name = "resetStyleLikeTask", lockAtLeastFor = "50s", lockAtMostFor = "10m")
    public void resetStyleLike() {
        log.info("스타일 작업 실행");
        styleRepository.resetAllTotalLike();
        log.info("스크립트 좋아요 reset");
    }
}
