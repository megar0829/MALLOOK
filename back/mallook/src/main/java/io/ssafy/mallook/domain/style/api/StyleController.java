package io.ssafy.mallook.domain.style.api;

import io.ssafy.mallook.domain.member.dto.request.MemberDetailReq;
import io.ssafy.mallook.domain.style.application.StyleService;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.dto.request.StyleInsertReq;
import io.ssafy.mallook.domain.style.dto.response.StyleDetailRes;
import io.ssafy.mallook.domain.style.dto.response.StyleListRes;
import io.ssafy.mallook.domain.style.dto.response.StylePageRes;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/styles")
@RequiredArgsConstructor
@Log4j2
public class StyleController {
    private final StyleService styleService;
    @Operation(
            summary = "코디 등록",
            responses = {
                    @ApiResponse(responseCode = "200", description = "코디 등록 성공"),
                    @ApiResponse(responseCode = "404", description = "코디 등록 실패")
            }
    )
    @PostMapping
    public ResponseEntity<BaseResponse<String>> saveStyle(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @Valid @RequestBody StyleInsertReq styleInsertReq){
        styleService.saveStyle(userSecurityDTO.getId(), styleInsertReq);
        return BaseResponse.success(
                SuccessCode.INSERT_SUCCESS,
                "코디 등록 성공"
        );
    }
    @Operation(
            summary = "코디 목록 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "코디 등록 성공"),
                    @ApiResponse(responseCode = "404", description = "코디 등록 실패")
            }
    )
    @GetMapping
    public ResponseEntity<BaseResponse<StylePageRes>> findStyleList(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @PageableDefault(sort="id", direction = Sort.Direction.DESC, page=0) Pageable pageable){
        var result = styleService.findStyleList(pageable);
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                result
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<StyleDetailRes>> findStyleDetail(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @PathVariable("id") Long id){
        var result = styleService.findStyleDetail(id);
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                result
        );
    }
    @DeleteMapping
    public ResponseEntity<BaseResponse<String>> deleteMyStyle(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @RequestBody Long styleId){
        styleService.DeleteStyle(userSecurityDTO.getId(), styleId);
        return BaseResponse.success(
                SuccessCode.DELETE_SUCCESS,
                "스타일 삭제 성공"
        );
    }
}
