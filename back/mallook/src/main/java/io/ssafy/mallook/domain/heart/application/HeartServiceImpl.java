package io.ssafy.mallook.domain.heart.application;

import io.ssafy.mallook.domain.heart.dao.HeartRepository;
import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.heart.entity.Heart;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import io.ssafy.mallook.domain.script.entity.Script;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.entity.Style;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HeartServiceImpl implements HeartService {

    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final ScriptRepository scriptRepository;
    private final StyleRepository styleRepository;

    @Override
    public Page<ScriptListDto> getLikeScriptList(UUID id, Pageable pageable) {
        Member proxyMember = memberRepository.getReferenceById(id);

        return heartRepository.findAllByMember(proxyMember, pageable)
                .map(Heart::getScript)
                .map(ScriptListDto::toDto);
    }

    @Override
    @Transactional
    public void likeScript(UUID id, LikeDto likeDto) {
        Member proxyMember = memberRepository.getReferenceById(id);
        Script proxyScript = scriptRepository.getReferenceById(likeDto.targetId());
        heartRepository.findByMemberAndScript(proxyMember, proxyScript)
                .ifPresent(like -> {
                    throw new BaseExceptionHandler(ErrorCode.DUPLICATE_LIKE);
                });

        proxyScript.like();
        heartRepository.save(likeDto.toEntity(proxyMember, proxyScript));
    }

    @Override
    @Transactional
    public void likeStyle(UUID id, LikeDto likeDto) {
        Member proxyMember = memberRepository.getReferenceById(id);
        Style proxyStyle = styleRepository.getReferenceById(likeDto.targetId());
        heartRepository.findByMemberAndStyle(proxyMember, proxyStyle)
                .ifPresent(style -> {
                    throw new BaseExceptionHandler(ErrorCode.DUPLICATE_LIKE);
                });

        heartRepository.save(likeDto.toEntity(proxyMember, proxyStyle));
    }

    @Override
    @Transactional
    public void unlikeScript(UUID id, LikeDto likeDto) {
        Member proxyMember = memberRepository.getReferenceById(id);
        Script proxyScript = scriptRepository.getReferenceById(likeDto.targetId());
        Heart heart = heartRepository.findByMemberAndScript(proxyMember, proxyScript)
                .orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_LIKE));

        proxyScript.unlike();
        heartRepository.deleteById(heart.getId());
    }

    @Override
    public void unlikeStyle(UUID id, LikeDto likeDto) {
        Member proxyMember = memberRepository.getReferenceById(id);
        Style proxyStyle = styleRepository.getReferenceById(likeDto.targetId());
        Heart heart = heartRepository.findByMemberAndStyle(proxyMember, proxyStyle)
                .orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_LIKE));

        heartRepository.deleteById(heart.getId());
    }
}
