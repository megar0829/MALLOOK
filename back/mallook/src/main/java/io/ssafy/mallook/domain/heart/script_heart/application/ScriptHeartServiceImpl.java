package io.ssafy.mallook.domain.heart.script_heart.application;

import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.heart.script_heart.dao.ScriptHeartRepository;
import io.ssafy.mallook.domain.heart.script_heart.entity.ScriptHeart;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import io.ssafy.mallook.domain.script.entity.Script;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScriptHeartServiceImpl implements ScriptHeartService {

    private final ScriptHeartRepository scriptHeartRepository;
    private final MemberRepository memberRepository;
    private final ScriptRepository scriptRepository;

    @Override
    public Slice<ScriptListDto> getLikeScriptList(Long cursor, UUID id, Pageable pageable) {
        Member proxyMember = memberRepository.getReferenceById(id);

        return scriptHeartRepository.findByIdLessThanAndMemberOrderByIdDesc(cursor, proxyMember, pageable)
                .map(ScriptHeart::getScript)
                .map(ScriptListDto::toDto);
    }

    @Override
    @Transactional
    public void likeScript(UUID id, LikeDto likeDto) {
        Member proxyMember = memberRepository.getReferenceById(id);
        Script proxyScript = scriptRepository.getReferenceById(likeDto.targetId());
        proxyScript.like();
        scriptHeartRepository.findByMemberAndScript(proxyMember, proxyScript)
                .ifPresent(like -> {
                    throw new BaseExceptionHandler(ErrorCode.DUPLICATE_LIKE);
                });

        scriptHeartRepository.save(likeDto.toEntity(proxyMember, proxyScript));
    }

    @Override
    @Transactional
    public void unlikeScript(UUID id, LikeDto likeDto) {
        Member proxyMember = memberRepository.getReferenceById(id);
        Script proxyScript = scriptRepository.getReferenceById(likeDto.targetId());
        proxyScript.unlike();
        ScriptHeart heart = scriptHeartRepository.findByMemberAndScript(proxyMember, proxyScript)
                .orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_LIKE));

        scriptHeartRepository.deleteById(heart.getId());
    }

    @Override
    public Long findMaxHeartId() {
        return scriptHeartRepository.findMaxHeartId();
    }
}
