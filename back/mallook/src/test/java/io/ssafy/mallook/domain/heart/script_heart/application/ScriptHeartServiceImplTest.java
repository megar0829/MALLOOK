package io.ssafy.mallook.domain.heart.script_heart.application;

import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.heart.script_heart.dao.ScriptHeartRepository;
import io.ssafy.mallook.domain.heart.script_heart.entity.ScriptHeart;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import io.ssafy.mallook.domain.script.entity.Script;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScriptHeartServiceImplTest {

    @Mock
    private ScriptHeartRepository scriptHeartRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ScriptRepository scriptRepository;

    @InjectMocks
    private ScriptHeartServiceImpl scriptHeartService;

    private Member member;
    private Script script;

    @BeforeEach
    void setUp() {
        member = mock(Member.class);
        script = mock(Script.class);
    }

    @Test
    void getLikeScriptListTest() {
        when(scriptHeartRepository.findByIdLessThanAndMemberOrderByIdDesc(any(Long.class), any(Member.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));
        when(memberRepository.getReferenceById(any(UUID.class))).thenReturn(member);

        scriptHeartService.getLikeScriptList(1L, UUID.randomUUID(), Pageable.unpaged());

        verify(scriptHeartRepository, times(1)).findByIdLessThanAndMemberOrderByIdDesc(any(Long.class), any(Member.class), any(Pageable.class));
    }

    @Test
    void likeScriptTest() {
        when(memberRepository.getReferenceById(any(UUID.class))).thenReturn(member);
        when(scriptRepository.getReferenceById(anyLong())).thenReturn(script);
        when(scriptHeartRepository.findByMemberAndScript(any(Member.class), any(Script.class))).thenReturn(Optional.empty());

        scriptHeartService.likeScript(UUID.randomUUID(), new LikeDto(1L));

        verify(scriptHeartRepository, times(1)).save(any());
    }

    @Test
    void likeScriptDuplicateTest() {
        when(memberRepository.getReferenceById(any(UUID.class))).thenReturn(member);
        when(scriptRepository.getReferenceById(anyLong())).thenReturn(script);
        when(scriptHeartRepository.findByMemberAndScript(any(Member.class), any(Script.class))).thenReturn(Optional.of(mock(ScriptHeart.class)));

        assertThatThrownBy(() -> scriptHeartService.likeScript(UUID.randomUUID(), new LikeDto(1L)))
                .isInstanceOf(BaseExceptionHandler.class);
    }

    @Test
    void unlikeScriptTest() {
        when(memberRepository.getReferenceById(any(UUID.class))).thenReturn(member);
        when(scriptRepository.getReferenceById(anyLong())).thenReturn(script);
        when(scriptHeartRepository.findByMemberAndScript(any(Member.class), any(Script.class))).thenReturn(Optional.of(mock(ScriptHeart.class)));

        scriptHeartService.unlikeScript(UUID.randomUUID(), new LikeDto(1L));

        verify(scriptHeartRepository, times(1)).deleteById(any());
    }

    @Test
    void unlikeScriptNotFoundTest() {
        when(memberRepository.getReferenceById(any(UUID.class))).thenReturn(member);
        when(scriptRepository.getReferenceById(anyLong())).thenReturn(script);
        when(scriptHeartRepository.findByMemberAndScript(any(Member.class), any(Script.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> scriptHeartService.unlikeScript(UUID.randomUUID(), new LikeDto(1L)))
                .isInstanceOf(BaseExceptionHandler.class);
    }
}
