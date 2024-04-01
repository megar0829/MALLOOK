package io.ssafy.mallook.domain.keyword.api;

import io.ssafy.mallook.domain.keyword.application.KeywordService;
import io.ssafy.mallook.domain.keyword.dto.response.KeywordListRes;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/keywords")
@Tag(name = "키워드", description = "키워드 관련 API")
public class KeywordController {

    private final KeywordService keywordService;

    @Operation(summary = "키워드 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "키워드 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "키워드 조회 실패")
            })
    @GetMapping
    public ResponseEntity<BaseResponse<List<KeywordListRes>>> getKeywordList() {
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                keywordService.getKeywordList()
        );
    }

    @Operation(summary = "탑 10 키워드 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "키워드 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "키워드 조회 실패")
            })
    @GetMapping("/top-ten")
    public ResponseEntity<BaseResponse<List<KeywordListRes>>> getTopTenKeywordList() {
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                keywordService.getTopTenKeywordList());
    }
}
