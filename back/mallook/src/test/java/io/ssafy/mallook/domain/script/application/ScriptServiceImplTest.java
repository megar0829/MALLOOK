package io.ssafy.mallook.domain.script.application;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.dto.request.ScriptCreatDto;
import io.ssafy.mallook.domain.script.dto.request.ScriptDeleteListDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptDetailDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import io.ssafy.mallook.domain.script.entity.Script;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScriptServiceImplTest {

    @Mock
    private ScriptRepository scriptRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private ScriptServiceImpl scriptService;

    private Script script;
    private Member member;

    @BeforeEach
    void setUp() {
        member = Mockito.mock(Member.class);
        memberRepository.save(member);
        script = buildScript(member);
    }

    private Script buildScript(Member member) {
        return Script.builder()
                .name("테스트용 스크립트")
                .member(member)
                .heartCount(0)
                .build();
    }

    @Test
    @DisplayName("스크립트 목록 조회 테스트")
    void getScriptList() {
        // given
        Pageable pageable = PageRequest.of(0, 2);
        UUID memberId = member.getId();
        Member proxyMember = memberRepository.getReferenceById(memberId);

        // When
        Page<Script> emptyPage = new PageImpl<>(Collections.emptyList());
        Mockito.when(scriptRepository.findAllByMember(proxyMember, pageable)).thenReturn(emptyPage);
        Page<ScriptListDto> result = scriptService.getScriptList(memberId, pageable);

        // then
        Mockito.verify(scriptRepository, Mockito.times(1)).findAllByMember(proxyMember, pageable);
        Mockito.verify(memberRepository, Mockito.times(2)).getReferenceById(memberId);
    }

    @Test
    @DisplayName("스크립트 상세 조회 테스트")
    void getScriptDetail() {
        // given
        Long scriptId = script.getId();

        // when
        Mockito.when(scriptRepository.findById(scriptId)).thenReturn(Optional.of(script));
        ScriptDetailDto result = scriptService.getScriptDetail(scriptId);

        Mockito.verify(scriptRepository, Mockito.times(1)).findById(scriptId);
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("스크립트 생성 api 테스트")
    void createScript() {
        // given
        ScriptCreatDto scriptCreateDto = new ScriptCreatDto("테스트용 스크립트입니다");
        UUID id = member.getId();
        // when
        scriptService.createScript(scriptCreateDto, id);
        // then
        Mockito.verify(scriptRepository, Mockito.times(1)).save(Mockito.any(Script.class));
    }

    @Test
    @DisplayName("스크립트 삭제 api 테스트")
    void deleteScript() {
        // Given
        List<Long> deleteList = new ArrayList<>();
        ScriptDeleteListDto scriptDeleteListDto = new ScriptDeleteListDto(deleteList);

        // When
        scriptService.deleteScript(scriptDeleteListDto);

        // Then
        Mockito.verify(scriptRepository, Mockito.times(1)).deleteScript(Mockito.anyList());
    }
}

