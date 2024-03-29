package io.ssafy.mallook.global.batch.tasklet;

import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.entity.Script;
import io.ssafy.mallook.global.batch.dao.Top50RedisDao;
import io.ssafy.mallook.global.batch.dto.TopScriptDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateRedisValueTasklet implements Tasklet {

    private final ScriptRepository scriptRepository;
    private final Top50RedisDao top50RedisDao;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<Script> topScript = scriptRepository.findTop50ScriptsOrderByTotalLikeDesc();
        TopScriptDto scriptDto = TopScriptDto.builder()
                .scripts(topScript)
                .build();
        top50RedisDao.saveScripts(scriptDto, 35);
        return RepeatStatus.FINISHED;
    }
}
