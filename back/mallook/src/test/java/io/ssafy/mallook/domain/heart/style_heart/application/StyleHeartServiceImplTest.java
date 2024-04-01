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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
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
        styleId = 1L;
        member = new Member();
        style = Mockito.mock(Style.class);

        when(memberRepository.getReferenceById(memberId)).thenReturn(member);
        when(styleRepository.getReferenceById(styleId)).thenReturn(style);
    }

    @Test
    void likeStyle_Success() {
        LikeDto likeDto = new LikeDto(styleId);

        when(styleHeartRepository.findByMemberAndStyle(any(Member.class), any(Style.class)))
                .thenReturn(Optional.empty());

        styleHeartService.likeStyle(memberId, likeDto);

        verify(styleHeartRepository, times(1)).save(any());
    }

    @Test
    void likeStyle_Fail_When_Duplicate() {
        LikeDto likeDto = new LikeDto(styleId);

        when(styleHeartRepository.findByMemberAndStyle(any(Member.class), any(Style.class)))
                .thenReturn(Optional.of(mock(StyleHeart.class)));

        assertThrows(BaseExceptionHandler.class, () -> {
            styleHeartService.likeStyle(memberId, likeDto);
        });
    }

    @Test
    void unlikeStyle_Success() {
        LikeDto likeDto = new LikeDto(styleId);
        StyleHeart styleHeart = mock(StyleHeart.class);

        when(styleHeartRepository.findByMemberAndStyle(any(Member.class), any(Style.class)))
                .thenReturn(Optional.of(styleHeart));

        styleHeartService.unlikeStyle(memberId, likeDto);

        verify(styleHeartRepository, times(1)).deleteById(any());
    }

    @Test
    void findMaxHeartId() {
        when(styleHeartRepository.findMaxHeartId()).thenReturn(10L);

        Long maxId = styleHeartService.findMaxHeartId();

        verify(styleHeartRepository, times(1)).findMaxHeartId();
        assertEquals(10L, maxId);
    }
}
