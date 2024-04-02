package io.ssafy.mallook.domain.script.application;

import io.ssafy.mallook.domain.chatgpt.dto.request.QuestionDto;
import io.ssafy.mallook.domain.chatgpt.dto.response.GptResponseDto;
import io.ssafy.mallook.domain.chatgpt.service.GptService;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.dto.request.ScriptCreatDto;
import io.ssafy.mallook.domain.script.dto.request.ScriptDeleteListDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptDetailDto;
import io.ssafy.mallook.domain.script.entity.Script;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScriptServiceImplTest {

    @Mock
    private ScriptRepository scriptRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private ScriptServiceImpl scriptService;

    @Mock
    private GptService gptService;

    private Script script;
    private Member member;

    @BeforeEach
    void setUp() {
        member = mock(Member.class);
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
        Long cursor = 21L;
        boolean hasNext = false;
        Pageable pageable = PageRequest.of(0, 20);
        List<Script> list = new ArrayList<>();
        UUID memberId = member.getId();
        Member proxyMember = memberRepository.getReferenceById(memberId);

        // When
        Slice<Script> emptyPage = new SliceImpl<>(list, pageable, hasNext);
        when(scriptRepository.findByIdLessThanAndMemberOrderByIdDesc(cursor, proxyMember, pageable))
                .thenReturn(emptyPage);
        scriptService.getScriptList(cursor, memberId, pageable);

        // then
        verify(scriptRepository, times(1)).findByIdLessThanAndMemberOrderByIdDesc(cursor, proxyMember, pageable);
        verify(memberRepository, times(2)).getReferenceById(memberId);
    }

//    @Test
//    @DisplayName("스크립트 상세 조회 테스트")
//    void getScriptDetail() {
//        // given
//        Long scriptId = script.getId();
//
//        // when
//        when(scriptRepository.findById(scriptId)).thenReturn(Optional.of(script));
//        ScriptDetailDto result = scriptService.getScriptDetail(scriptId);
//
//        verify(scriptRepository, times(1)).findById(scriptId);
//        assertThat(result).isNotNull();
//    }

    @Test
    @DisplayName("스크립트 생성 api 테스트")
    void createScript() {
        // given
        List<String> keywordList = new ArrayList<>();
        keywordList.add("예쁜");
        keywordList.add("멋진");
        ScriptCreatDto scriptCreateDto = ScriptCreatDto.builder()
                .keywordsList(keywordList)
                .build();
        UUID id = member.getId();
        GptResponseDto fakeResponse = GptResponseDto.builder()
                .answer("테스트응답")
                .build();

        given(gptService.askQuestion(any(QuestionDto.class)))
                .willReturn(fakeResponse);

        // when
        scriptService.createScript(scriptCreateDto, id);
        // then
        verify(scriptRepository, times(1)).save(any(Script.class));
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
        verify(scriptRepository, times(1)).deleteScript(anyList());
    }
}

