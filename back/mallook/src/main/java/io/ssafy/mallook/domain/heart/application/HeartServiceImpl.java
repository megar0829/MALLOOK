package io.ssafy.mallook.domain.heart.application;

import io.ssafy.mallook.domain.heart.dao.HeartRepository;
import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.entity.Script;
import lombok.RequiredArgsConstructor;
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

    @Override
    @Transactional
    public void likeScript(UUID id, LikeDto likeDto) {
        Member proxyMember = memberRepository.getReferenceById(id);
        Script proxyScript = scriptRepository.getReferenceById(likeDto.targetId());
        heartRepository.save(likeDto.toEntity(proxyMember, proxyScript));
    }

    @Override
    @Transactional
    public void unlikeScript(UUID id, LikeDto likeDto) {
        Member proxyMember = memberRepository.getReferenceById(id);
        Script proxyScript = scriptRepository.getReferenceById(likeDto.targetId());
        heartRepository.deleteByMemberAndScript(proxyMember, proxyScript);
    }
}
