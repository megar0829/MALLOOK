package io.ssafy.mallook.domain.heart.application;

import io.ssafy.mallook.domain.heart.dao.HeartRepository;
import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.heart.entity.Heart;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.entity.Script;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.entity.Style;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class HeartServiceImplTest {

    @Mock
    private HeartRepository heartRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private StyleRepository styleRepository;

    @Mock
    private ScriptRepository scriptRepository;

    @InjectMocks
    private HeartServiceImpl heartService;

    private Script script;
    private Style style;
    private Member member;

    @BeforeEach
    void setUp() {
        member = Mockito.mock(Member.class);
        memberRepository.save(member);
        script = buildScript(member);
        scriptRepository.save(script);
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

    private Script buildScript(Member member) {
        return Script.builder()
                .name("테스트 스크립트")
                .member(member)
                .heartCount(0)
                .build();
    }

    private Heart buildHeart(Member member, Script script, Style style) {
        return Heart.builder()
                .member(member)
                .script(script)
                .style(style)
                .build();
    }

    @Test
    void getLikeStyleList() {
        // given
        Long cursor = 21L;
        boolean hasNext = false;
        Pageable pageable = PageRequest.of(0, 20);
        List<Heart> list = new ArrayList<>();
        UUID id = member.getId();
        Member proxyMember = memberRepository.getReferenceById(id);
        // when
        Slice<Heart> emptyPage = new SliceImpl<>(list, pageable, hasNext);
        Mockito.when(heartRepository.findByIdLessThanAndMemberAndScriptIsNullOrderByIdDesc(cursor, proxyMember, pageable))
                .thenReturn(emptyPage);

        heartService.getLikeStyleList(cursor, id, pageable);
        // then
        Mockito.verify(heartRepository, Mockito.times(1)).findByIdLessThanAndMemberAndScriptIsNullOrderByIdDesc(cursor, proxyMember, pageable);
        Mockito.verify(memberRepository, Mockito.times(2)).getReferenceById(id);
    }

    @Test
    void likeScript() {
        UUID id = member.getId();
        LikeDto likeDto = new LikeDto(script.getId());

        heartService.likeScript(id, likeDto);

        Mockito.verify(heartRepository, Mockito.times(1)).save(Mockito.any(Heart.class));
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
    void unlikeScript() {
        Heart heart = buildHeart(member, script, style);
        Mockito.when(heartRepository.findByMemberAndScript(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.of(heart)); // Mock 객체 설정 변경

        heartRepository.save(heart);
        UUID id = member.getId();
        LikeDto likeDto = new LikeDto(script.getId());

        // when
        heartService.unlikeScript(id, likeDto);

        // then
        Mockito.verify(heartRepository, Mockito.times(1)).deleteById(heart.getId());

    }

    @Test
    void unlikeStyle() {
        Heart heart = buildHeart(member, script, style);
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