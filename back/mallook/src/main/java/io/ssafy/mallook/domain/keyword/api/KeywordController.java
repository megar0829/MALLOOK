package io.ssafy.mallook.domain.keyword.api;

import io.ssafy.mallook.domain.keyword.application.KeywordService;
import io.ssafy.mallook.domain.keyword.dto.response.KeywordListRes;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/keywords")
public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<KeywordListRes>>> getKeywordList() {

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                keywordService.getKeywordList()
        );
    }
}
