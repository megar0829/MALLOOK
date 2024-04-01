package io.ssafy.mallook.domain.keyword.application;

import io.ssafy.mallook.domain.keyword.application.KeywordServiceImpl;
import io.ssafy.mallook.domain.keyword.dto.response.KeywordListRes;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.entity.Script;
import io.ssafy.mallook.global.batch.dao.Top50RedisDao;
import io.ssafy.mallook.global.batch.dto.TopScriptDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KeywordServiceImplTest {

    @Mock
    private ScriptRepository scriptRepository;

    @Mock
    private Top50RedisDao top50RedisDao;

    @InjectMocks
    private KeywordServiceImpl keywordService;

    @Test
    @DisplayName("키워드 리스트 불러오기 테스트")
    void getKeywordListTest() {
        List<Long> mockPkList = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L);
        when(top50RedisDao.getScriptsDto()).thenReturn(new TopScriptDto(mockPkList));

        List<Script> mockScripts = new ArrayList<>();
        for (int i = 0; i < mockPkList.size(); i++) {
            Script script = mock(Script.class);
            when(script.getKeywordList()).thenReturn(Arrays.asList("Keyword" + i));
            mockScripts.add(script);
        }
        when(scriptRepository.findAllById(mockPkList)).thenReturn(mockScripts);

        // when
        List<KeywordListRes> result = keywordService.getKeywordList();

        // then
        assertEquals(8, result.size());
        verify(scriptRepository, times(1)).findAllById(mockPkList);
    }

    @Test
    void getTopTenKeywordListTest() {
        List<Long> mockPkList = Arrays.asList(1L, 2L, 3L, 4L);
        when(top50RedisDao.getScriptsDto()).thenReturn(new TopScriptDto(mockPkList));

        List<Script> mockScripts = new ArrayList<>();
        for (int i = 0; i < mockPkList.size(); i++) {
            Script script = mock(Script.class);
            List<String> keywords = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                keywords.add("Keyword" + ((i * 5) + j));
            }
            when(script.getKeywordList()).thenReturn(keywords);
            mockScripts.add(script);
        }
        when(scriptRepository.findAllById(mockPkList)).thenReturn(mockScripts);

        // when
        List<KeywordListRes> result = keywordService.getTopTenKeywordList();

        // then
        assertEquals(10, result.size());
        verify(scriptRepository, times(1)).findAllById(mockPkList);
    }
}
