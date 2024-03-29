package io.ssafy.mallook.domain.style.api;

import io.ssafy.mallook.domain.style.application.StyleService;
import io.ssafy.mallook.domain.style.dto.request.StyleDeleteReq;
import io.ssafy.mallook.domain.style.dto.request.StyleInsertReq;
import io.ssafy.mallook.domain.style.dto.response.StyleDetailRes;
import io.ssafy.mallook.domain.style.dto.response.StyleRes;
import io.ssafy.mallook.domain.style.dto.response.StyledWorldCupDto;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/styles")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "스타일", description = "스타일 관련 API")
public class StyleController {

    private final StyleService styleService;

    @Operation(
            summary = "코디 목록 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "코디 목록 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "코디 목록 조회 실패")
            }
    )
    @GetMapping
    public ResponseEntity<BaseResponse<Slice<StyleRes>>> findStyleList(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC, page = 0) Pageable pageable,
            @RequestParam(required = false) Long cursor) {
        var result = Objects.nonNull(cursor) ? styleService.findStyleList(pageable, cursor)
                : styleService.findStyleListFirst(pageable);
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                result
        );
    }
    @Operation(
            summary = "월드컵 리스트 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "월드컵 리스트 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "월드컵 리스트 조회 실패")
            }
    )
    @GetMapping("/world-cup")
    public ResponseEntity<BaseResponse<List<StyledWorldCupDto>>> getWorldCupList(
    ) {
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                styleService.getWorldCupList()
        );
    }

    @Operation(
            summary = "코디 상세 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "코디 상세 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "코디 상세 조회 실패")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<StyleDetailRes>> findStyleDetail(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @PathVariable("id") Long id) {
        var result = styleService.findStyleDetail(id);
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                result
        );
    }

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
            @Valid @RequestBody StyleInsertReq styleInsertReq) {
        styleService.saveStyle(userSecurityDTO.getId(), styleInsertReq);
        return BaseResponse.success(
                SuccessCode.INSERT_SUCCESS,
                "코디 등록 성공"
        );
    }

    @Operation(
            summary = "코디 삭제",
            responses = {
                    @ApiResponse(responseCode = "200", description = "코디 삭제 성공"),
                    @ApiResponse(responseCode = "404", description = "코디 삭제 실패")
            }
    )
    @DeleteMapping
    public ResponseEntity<BaseResponse<String>> deleteMyStyle(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @RequestBody StyleDeleteReq styleDeleteReq) {
        styleService.DeleteStyle(userSecurityDTO.getId(), styleDeleteReq.styleIdList());
        return BaseResponse.success(
                SuccessCode.DELETE_SUCCESS,
                "스타일 삭제 성공"
        );
    }
}
