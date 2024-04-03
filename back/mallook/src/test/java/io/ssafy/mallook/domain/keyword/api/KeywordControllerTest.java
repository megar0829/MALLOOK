package io.ssafy.mallook.domain.keyword.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ssafy.mallook.config.security.WithMockCustomUser;
import io.ssafy.mallook.domain.keyword.application.KeywordService;
import io.ssafy.mallook.domain.keyword.dto.response.KeywordListRes;
import io.ssafy.mallook.global.common.code.SuccessCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(KeywordController.class)
public class KeywordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KeywordService keywordService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    public void getKeywordListTest() throws Exception {
        List<KeywordListRes> mockKeywords = Arrays.asList(new KeywordListRes("keyword1"), new KeywordListRes("keyword2"));
        given(keywordService.getKeywordList()).willReturn(mockKeywords);

        mockMvc.perform(get("/api/keywords")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(SuccessCode.SELECT_SUCCESS.getStatus()))
                .andExpect(jsonPath("$.message").value(SuccessCode.SELECT_SUCCESS.getMessage()))
                .andExpect(jsonPath("$.result[0].name").value("keyword1"));
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    public void getTopTenKeywordListTest() throws Exception {
        List<KeywordListRes> mockTopKeywords = Arrays.asList(new KeywordListRes("topKeyword1"), new KeywordListRes("topKeyword2"));
        given(keywordService.getTopTenKeywordList()).willReturn(mockTopKeywords);

        mockMvc.perform(get("/api/keywords/top-ten")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(SuccessCode.SELECT_SUCCESS.getStatus()))
                .andExpect(jsonPath("$.message").value(SuccessCode.SELECT_SUCCESS.getMessage()))
                .andExpect(jsonPath("$.result[0].name").value("topKeyword1"));
    }
}
