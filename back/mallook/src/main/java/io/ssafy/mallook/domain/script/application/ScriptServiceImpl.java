package io.ssafy.mallook.domain.script.application;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.dto.request.ScriptCreatDto;
import io.ssafy.mallook.domain.script.dto.request.ScriptDeleteListDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptDetailDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ScriptServiceImpl implements ScriptService {

    private final MemberRepository memberRepository;
    private final ScriptRepository scriptRepository;

    @Override
    public Page<ScriptListDto> getScriptList(UUID id, Pageable pageable) {
        Member proxyMember = memberRepository.getReferenceById(id);

        return scriptRepository.findAllByMemberAndStatusTrue(proxyMember, pageable)
                .map(ScriptListDto::toDto);
    }

    @Override
    public ScriptDetailDto getScriptDetail(Long scriptId) {
        return scriptRepository.findByIdAndStatusTrue(scriptId)
                .map(ScriptDetailDto::toDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void createScript(ScriptCreatDto scriptCreateDto, UUID id) {
        Member proxyMember = memberRepository.getReferenceById(id);
        log.info("제목: " + scriptCreateDto.scriptContent());

        scriptRepository.save(scriptCreateDto.toEntity(proxyMember));
    }

    @Transactional
    public void deleteScript(ScriptDeleteListDto scriptDeleteListDto) {
        log.info(scriptDeleteListDto.toString());
        scriptRepository.deleteScript(scriptDeleteListDto.toDeleteList());
    }
}
