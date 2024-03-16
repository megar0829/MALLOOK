package io.ssafy.mallook.domain.heart.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ssafy.mallook.config.security.WithMockCustomUser;
import io.ssafy.mallook.domain.heart.application.HeartService;
import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import io.ssafy.mallook.domain.style.dto.response.StyleListRes;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(controllers = HeartController.class)
class HeartControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeartService heartService;

    private String scriptLikeUrl = "/api/hearts/scripts";

    private String styleLikeUrl = "/api/hearts/styles";

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void getLikedScriptList() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get(scriptLikeUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SELECT SUCCESS"));
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void getLikeStyleList() throws Exception {
        List<StyleListRes> list = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 2);
        Page<StyleListRes> page = new PageImpl<>(list, pageable, list.size());
        Mockito.when(heartService.getLikeStyleList(any(UUID.class), eq(pageable)))
                .thenReturn(page);

        mockMvc.perform(
                        MockMvcRequestBuilders.get(styleLikeUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("SELECT SUCCESS"));
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void likeScript() throws Exception {
        LikeDto likeDto = new LikeDto(1L);

        mockMvc.perform(MockMvcRequestBuilders.post(scriptLikeUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(likeDto))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("INSERT SUCCESS")
                );
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void likeStyle() throws Exception {
        LikeDto likeDto = new LikeDto(1L);

        mockMvc.perform(MockMvcRequestBuilders.post(styleLikeUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(likeDto))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("INSERT SUCCESS")
                );
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void unlikeScript() throws Exception {
        LikeDto likeDto = new LikeDto(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete(scriptLikeUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(likeDto))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("DELETE SUCCESS")
                );
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void unlikeStyle() throws Exception {
        LikeDto likeDto = new LikeDto(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete(styleLikeUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(likeDto))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("DELETE SUCCESS")
                );
    }
}