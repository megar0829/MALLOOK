package io.ssafy.mallook.domain.heart.application;

import io.ssafy.mallook.domain.heart.dao.HeartRepository;
import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.heart.entity.Heart;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.dto.response.StyleListRes;
import io.ssafy.mallook.domain.style.entity.Style;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HeartServiceImplTest {

    @Mock
    private HeartRepository heartRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private StyleRepository styleRepository;

    @InjectMocks
    private HeartServiceImpl heartService;

    private Style style;
    private Member member;

    @BeforeEach
    void setUp() {
        member = Mockito.mock(Member.class);
        memberRepository.save(member);
        style = buildStyle(member);
        styleRepository.save(style);
    }

    private Style buildStyle(Member member) {
        return Style.builder()
                .name("테스트 스타일")
                .member(member)
                .heartCount(0L)
                .build();
    }

    private Heart buildHeart(Member member, Style style) {
         return Heart.builder()
                .member(member)
                .style(style)
                .build();
    }

    @Test
    void getLikeStyleList() {
        // given
        Pageable pageable = PageRequest.of(0, 2);
        UUID id = member.getId();
        Member proxyMember = memberRepository.getReferenceById(id);
        // when
        Page<Heart> emptyPage = new PageImpl<>(Collections.emptyList());
        Mockito.when(heartRepository.findAllByMember(proxyMember, pageable)).thenReturn(emptyPage);
        Page<StyleListRes> result = heartService.getLikeStyleList(id, pageable);
        // then
        Mockito.verify(heartRepository, Mockito.times(1)).findAllByMember(proxyMember, pageable);
        Mockito.verify(memberRepository, Mockito.times(2)).getReferenceById(id);
    }

    @Test
    void likeStyle() {
        // given
        UUID id = member.getId();
        LikeDto likeDto = new LikeDto(style.getId());

        // when
        heartService.likeStyle(id, likeDto);

        // then
        Mockito.verify(heartRepository, Mockito.times(1)).save(Mockito.any(Heart.class));
    }

    @Test
    void unlikeStyle() {
        Heart heart = buildHeart(member, style);
        Mockito.when(heartRepository.findByMemberAndStyle(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.of(heart)); // Mock 객체 설정 변경

        heartRepository.save(heart);
        UUID id = member.getId();
        LikeDto likeDto = new LikeDto(style.getId());

        // when
        heartService.unlikeStyle(id, likeDto);

        // then
        Mockito.verify(heartRepository, Mockito.times(1)).deleteById(heart.getId());
    }
}