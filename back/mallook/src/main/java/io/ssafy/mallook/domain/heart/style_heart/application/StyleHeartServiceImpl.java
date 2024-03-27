package io.ssafy.mallook.domain.heart.style_heart.application;

import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.heart.style_heart.dao.StyleHeartRepository;
import io.ssafy.mallook.domain.heart.style_heart.entity.StyleHeart;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.dto.response.StyleListRes;
import io.ssafy.mallook.domain.style.entity.Style;
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
public class StyleHeartServiceImpl implements StyleHeartService {

    private final StyleHeartRepository styleHeartRepository;
    private final MemberRepository memberRepository;
    private final ScriptRepository scriptRepository;
    private final StyleRepository styleRepository;

    @Override
    public Slice<StyleListRes> getLikeStyleList(Long cursor, UUID id, Pageable pageable) {
        Member proxyMember = memberRepository.getReferenceById(id);

        return styleHeartRepository.findByIdLessThanAndMemberOrderByIdDesc(cursor + 1, proxyMember, pageable)
                .map(StyleHeart::getStyle)
                .map(StyleListRes::toDto);
    }

    @Override
    @Transactional
    public void likeStyle(UUID id, LikeDto likeDto) {
        Member proxyMember = memberRepository.getReferenceById(id);
        Style proxyStyle = styleRepository.getReferenceById(likeDto.targetId());
        proxyStyle.like();
        styleHeartRepository.findByMemberAndStyle(proxyMember, proxyStyle)
                .ifPresent(style -> {
                    throw new BaseExceptionHandler(ErrorCode.DUPLICATE_LIKE);
                });

        styleHeartRepository.save(likeDto.toEntity(proxyMember, proxyStyle));
    }

    @Override
    @Transactional
    public void unlikeStyle(UUID id, LikeDto likeDto) {
        Member proxyMember = memberRepository.getReferenceById(id);
        Style proxyStyle = styleRepository.getReferenceById(likeDto.targetId());
        proxyStyle.unlike();
        StyleHeart heart = styleHeartRepository.findByMemberAndStyle(proxyMember, proxyStyle)
                .orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_LIKE));

        styleHeartRepository.deleteById(heart.getId());
    }

    @Override
    public Long findMaxHeartId() {
        return styleHeartRepository.findMaxHeartId();
    }
}
