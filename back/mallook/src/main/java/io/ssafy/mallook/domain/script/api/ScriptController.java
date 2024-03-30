package io.ssafy.mallook.domain.script.api;

import com.theokanning.openai.service.OpenAiService;
import io.ssafy.mallook.domain.script.application.ScriptService;
import io.ssafy.mallook.domain.script.dto.request.ScriptCreatDto;
import io.ssafy.mallook.domain.script.dto.request.ScriptDeleteListDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptDetailDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scripts")
@Log4j2
@Tag(name = "스크립트", description = "스크립트 관련 API")
public class ScriptController {

    private final ScriptService scriptService;

    @Operation(
            summary = "전체 스크립트 목록 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "스크립트 목록 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "스크립트 목록 조회 실패")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<BaseResponse<Slice<ScriptListDto>>> getScriptList(
            @PageableDefault(size = 20,
                    sort = "id",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) Long cursor) {
        cursor = !Objects.isNull(cursor) ? cursor : scriptService.getMaxScriptId() + 1;

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                scriptService.getScriptList(cursor, pageable)
        );
    }


    @Operation(
            summary = "자신이 작성한 스크립트 목록 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "스크립트 목록 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "스크립트 목록 조회 실패")
            }
    )
    @GetMapping
    public ResponseEntity<BaseResponse<Slice<ScriptListDto>>> getScriptList(
            @AuthenticationPrincipal UserSecurityDTO principal,
            @PageableDefault(size = 20,
                    sort = "id",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) Long cursor) {

        cursor = !Objects.isNull(cursor) ? cursor : scriptService.getMaxScriptId() + 1;

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                scriptService.getScriptList(cursor, principal.getId(), pageable)
        );
    }

    @Operation(
            summary = "스크립트 상세 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "스크립트 상세 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "스크립트 상세 조회 실패")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ScriptDetailDto>> getScriptDetail(@PathVariable Long id) {
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                scriptService.getScriptDetail(id)
        );
    }

    @Operation(
            summary = "스크립트 생성",
            responses = {
                    @ApiResponse(responseCode = "200", description = "스크립트 생성 성공"),
                    @ApiResponse(responseCode = "404", description = "스크립트 생성 실패")
            }
    )
    @PostMapping
    public ResponseEntity<BaseResponse<String>> createScript(@AuthenticationPrincipal UserSecurityDTO principal,
                                                             @RequestBody @Valid ScriptCreatDto scriptCreateDto) {
        UUID id = principal.getId();
        scriptService.createScript(scriptCreateDto, id);

        return BaseResponse.success(
                SuccessCode.INSERT_SUCCESS,
                "스크립트가 생성되었습니다."
        );
    }

    @Operation(
            summary = "스크립트 삭제",
            responses = {
                    @ApiResponse(responseCode = "200", description = "스크립트 삭제 성공"),
                    @ApiResponse(responseCode = "404", description = "스크립트 삭제 실패")
            }
    )
    @DeleteMapping
    @PreAuthorize("@authService.authorizeToDeleteScript(#principal.getId(), #scriptDeleteListDto)")
    public ResponseEntity<BaseResponse<String>> deleteScript(@AuthenticationPrincipal UserSecurityDTO principal,
                                                             @RequestBody @Valid ScriptDeleteListDto scriptDeleteListDto) {
        log.info("삭제 시작");
        scriptService.deleteScript(scriptDeleteListDto);
        return BaseResponse.success(
                SuccessCode.DELETE_SUCCESS,
                "스크립트 삭제 성공"
        );
    }
}
