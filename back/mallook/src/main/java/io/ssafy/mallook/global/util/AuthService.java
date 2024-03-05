package io.ssafy.mallook.global.util;

import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.dto.request.ScriptDeleteListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final ScriptRepository scriptRepository;

    public boolean authorizeToReadScriptDetail(UUID memberId, Long scriptId) {
        log.info("내가 쓴 글인지 확인 시작");
        return scriptRepository.findByIdAndStatusTrue(scriptId)
                .orElseThrow().isWrittenByTargetMember(memberId);
    }

    public boolean authorizeToDeleteScript(UUID memberId, ScriptDeleteListDto scriptDeleteListDto) {
        log.info("내가 쓴 글인지 확인 시작");
        List<Long> scriptIdList = scriptDeleteListDto.toDeleteList();

        return scriptIdList.stream()
                .map(scriptRepository::findByIdAndStatusTrue)
                .allMatch(scriptOptional -> scriptOptional
                        .filter(script -> script.isWrittenByTargetMember(memberId))
                        .isPresent());
    }
}
