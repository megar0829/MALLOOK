package io.ssafy.mallook.domain.heart.style_heart.application;

import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.heart.style_heart.dao.StyleHeartRepository;
import io.ssafy.mallook.domain.heart.style_heart.entity.StyleHeart;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.entity.Style;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StyleHeartServiceImplTest {

    @Mock
    private StyleHeartRepository styleHeartRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private StyleRepository styleRepository;

    @InjectMocks
    private StyleHeartServiceImpl styleHeartService;

    private UUID memberId;
    private Long styleId;
    private Member member;
    private Style style;

    @BeforeEach
    void setUp() {
        member = Mockito.mock(Member.class);
        style = buildStyle(member);
        styleRepository.save(style);
    }

    @Test
    void findMaxHeartId() {
        when(styleHeartRepository.findMaxHeartId()).thenReturn(10L);

        Long maxId = styleHeartService.findMaxHeartId();

        verify(styleHeartRepository, times(1)).findMaxHeartId();
    }

    private Style buildStyle(Member member) {
        return Style.builder()
                .name("테스트용 제목")
                .member(member)
                .heartCount(0L)
                .totalLike(0)
                .build();
    }
}
