package io.ssafy.mallook.domain.heart.script_heart.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ssafy.mallook.config.security.WithMockCustomUser;
import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.heart.script_heart.application.ScriptHeartService;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScriptHeartController.class)
public class ScriptHeartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScriptHeartService scriptHeartService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserSecurityDTO mockPrincipal;

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void getLikedScriptList() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(scriptHeartService.getLikeScriptList(null, id, Pageable.unpaged()))
                .willReturn(new PageImpl<>(Collections.emptyList()));

        mockMvc.perform(get("/api/scripts/hearts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(SuccessCode.SELECT_SUCCESS.getStatus()))
                .andExpect(jsonPath("$.message").value(SuccessCode.SELECT_SUCCESS.getMessage()));
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void likeScript() throws Exception {
        LikeDto likeDto = new LikeDto(1L);

        mockMvc.perform(post("/api/scripts/hearts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(likeDto))
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(SuccessCode.INSERT_SUCCESS.getStatus()))
                .andExpect(jsonPath("$.message").value(SuccessCode.INSERT_SUCCESS.getMessage()));
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void unlikeScript() throws Exception {
        LikeDto likeDto = new LikeDto(1L);

        mockMvc.perform(delete("/api/scripts/hearts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(likeDto))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(SuccessCode.DELETE_SUCCESS.getStatus()))
                .andExpect(jsonPath("$.message").value(SuccessCode.DELETE_SUCCESS.getMessage()));
    }
}
