package io.ssafy.mallook.domain.script.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ssafy.mallook.config.security.WithMockCustomUser;
import io.ssafy.mallook.domain.product.application.ProductService;
import io.ssafy.mallook.domain.product.dto.response.ProductsListDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsPageRes;
import io.ssafy.mallook.domain.script.application.ScriptService;
import io.ssafy.mallook.domain.script.dto.request.ScriptCreatDto;
import io.ssafy.mallook.domain.script.dto.request.ScriptDeleteListDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptDetailDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import io.ssafy.mallook.global.common.code.SuccessCode;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(controllers = ScriptController.class)
class ScriptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScriptService scriptService;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void getScriptListTest() throws Exception {
        Long cursor = 10L;
        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "id"));
        List<ScriptListDto> scriptList = List.of(
                ScriptListDto.builder()
                        .id(1L)
                        .name("스크립트1")
                        .nickname("작성자1")
                        .heartCount(100)
                        .build());
        Slice<ScriptListDto> expectedSlice = new SliceImpl<>(scriptList, pageable, false);
        given(scriptService.getScriptList(cursor, pageable)).willReturn(expectedSlice);

        mockMvc.perform(get("/api/scripts/all")
                        .param("cursor", String.valueOf(cursor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(SuccessCode.SELECT_SUCCESS.getStatus()))
                .andExpect(jsonPath("$.message").value(SuccessCode.SELECT_SUCCESS.getMessage()))
                .andExpect(jsonPath("$.result.content[0].id").value(1L))
                .andExpect(jsonPath("$.result.content[0].name").value("스크립트1"))
                .andExpect(jsonPath("$.result.content[0].nickname").value("작성자1"))
                .andExpect(jsonPath("$.result.content[0].heartCount").value(100));
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void getScriptListTest2() throws Exception {
        Long cursor = 10L;
        UUID principalId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "id"));
        List<ScriptListDto> scriptList = List.of(
                new ScriptListDto(1L, "스크립트1", 100, "작성자1", "img")
        );
        Slice<ScriptListDto> expectedSlice = new SliceImpl<>(scriptList, pageable, false);

        // 서비스 메소드 호출 시 예상되는 반환 값 설정
        given(scriptService.getScriptList(cursor, principalId, pageable)).willReturn(expectedSlice);

        // API 호출 및 응답 검증
        mockMvc.perform(get("/api/scripts")
                        .param("cursor", String.valueOf(cursor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(SuccessCode.SELECT_SUCCESS.getStatus()))
                .andExpect(jsonPath("$.message").value(SuccessCode.SELECT_SUCCESS.getMessage()))
                .andExpect(jsonPath("$.result.content[0].id").value(1))
                .andExpect(jsonPath("$.result.content[0].name").value("스크립트1"))
                .andExpect(jsonPath("$.result.content[0].nickname").value("작성자1"))
                .andExpect(jsonPath("$.result.content[0].heartCount").value(100));
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void getLatestScriptTest() throws Exception {
        UUID principalId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ScriptListDto mockScriptListDto = new ScriptListDto(1L, "스크립트1", 100, "작성자1", "이미지");

        given(scriptService.getLatestScript(principalId)).willReturn(mockScriptListDto);

        mockMvc.perform(get("/api/scripts/my-latest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(SuccessCode.SELECT_SUCCESS.getStatus()))
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.name").value("스크립트1"))
                .andExpect(jsonPath("$.result.nickname").value("작성자1"))
                .andExpect(jsonPath("$.result.heartCount").value(100));
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void getScriptDetailRecommendTest() throws Exception {
        Long scriptId = 1L; // 예시 스크립트 ID
        String cursor = "cursorValue"; // 가정된 커서 값
        List<ProductsListDto> productList = new ArrayList<>(); // 상품 리스트

        ProductsPageRes productsPageRes = ProductsPageRes.builder()
                .content(productList)
                .nextCursor("nextCursorValue")
                .build();

        given(productService.getLastMongoProductsId()).willReturn("lastMongoProductsId");
        given(scriptService.getRecommendProductDetail(scriptId, cursor, PageRequest.of(0, 12, Sort.by(Sort.Direction.DESC, "id"))))
                .willReturn(productsPageRes);

        mockMvc.perform(get("/api/scripts/{scriptId}/product-detail", scriptId)
                        .param("cursor", cursor))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(SuccessCode.SELECT_SUCCESS.getStatus()))
                .andExpect(jsonPath("$.result.content").exists())
                .andExpect(jsonPath("$.result.nextCursor").value("nextCursorValue"));
    }

//    @Test
//    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
//    void getScriptDetailWithoutLoginTest() throws Exception {
//        Long id = 1L;
//        ScriptDetailDto scriptDetail = ScriptDetailDto.builder()
//                .name("Script Name")
//                .heartCount(100)
//                .nickname("Author Nickname")
//                .hasLiked(false)
//                .build();
//
//        given(scriptService.getScriptDetail(id)).willReturn(scriptDetail);
//
//        mockMvc.perform(get("/api/scripts/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value(SuccessCode.SELECT_SUCCESS.getStatus()))
//                .andExpect(jsonPath("$.result.name").value(scriptDetail.name()))
//                .andExpect(jsonPath("$.result.heartCount").value(scriptDetail.heartCount()))
//                .andExpect(jsonPath("$.result.nickname").value(scriptDetail.nickname()))
//                .andExpect(jsonPath("$.result.hasLiked").value(scriptDetail.hasLiked()));
//    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void getScriptDetailWithLoginTest() throws Exception {
        Long id = 1L;
        UUID memberId = UUID.randomUUID();

        ScriptDetailDto scriptDetail = ScriptDetailDto.builder()
                .name("Script Name")
                .heartCount(100)
                .nickname("Author Nickname")
                .hasLiked(true)
                .build();

        given(scriptService.getScriptDetail(any(UUID.class), any(Long.class))).willReturn(scriptDetail);

        mockMvc.perform(get("/api/scripts/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(SuccessCode.SELECT_SUCCESS.getStatus()))
                .andExpect(jsonPath("$.result.name").value(scriptDetail.name()))
                .andExpect(jsonPath("$.result.heartCount").value(scriptDetail.heartCount()))
                .andExpect(jsonPath("$.result.nickname").value(scriptDetail.nickname()))
                .andExpect(jsonPath("$.result.hasLiked").value(scriptDetail.hasLiked()));
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    void createScriptTest() throws Exception {
        ScriptCreatDto scriptCreateDto = ScriptCreatDto.builder()
                .keywordsList(List.of("키워드1", "키워드2"))
                .build();

        // given
        scriptService.createScript(scriptCreateDto, UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        // when & then
        mockMvc.perform(post("/api/scripts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scriptCreateDto))
                        .with(csrf()))
                .andExpect(status().isCreated()) // HTTP 201 상태 코드를 기대합니다.
                // JSON 응답의 특정 필드를 검증합니다.
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("INSERT SUCCESS"))
                .andExpect(jsonPath("$.result").value("스크립트가 생성되었습니다."));
    }

    @Test
    @WithMockCustomUser(id = "123e4567-e89b-12d3-a456-426614174000", role = "USER")
    public void deleteScriptTest() throws Exception {
        List<Long> toDeleteList = List.of(1L, 2L);
        ScriptDeleteListDto scriptDeleteListDto = new ScriptDeleteListDto(toDeleteList);

        mockMvc.perform(delete("/api/scripts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(scriptDeleteListDto))
                .with(csrf()))
                .andExpect(status().isOk());
    }
}
