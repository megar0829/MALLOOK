package io.ssafy.mallook.domain.script.api;

import io.ssafy.mallook.domain.script.application.ScriptService;
import io.ssafy.mallook.domain.script.dto.request.ScriptCreatDto;
import io.ssafy.mallook.domain.script.dto.request.ScriptDeleteListDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptDetailDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scripts")
@Log4j2
public class ScriptController {

    private final ScriptService scriptService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse<Page<ScriptListDto>>> getScriptList(@AuthenticationPrincipal UserSecurityDTO principal,
                                                                           @PageableDefault(size = 2,
                                                     sort = "createdAt",
                                                     direction = Sort.Direction.DESC) Pageable pageable) {
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                scriptService.getScriptList(principal.getId(), pageable)
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@authService.authorizeToReadScriptDetail(#principal.getId(), #id)")
    public ResponseEntity<BaseResponse<ScriptDetailDto>> getScriptDetail(@AuthenticationPrincipal UserSecurityDTO principal,
                                                                         @PathVariable Long id) {
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                scriptService.getScriptDetail(id)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponse<String>> createScript(@AuthenticationPrincipal UserSecurityDTO principal,
                                                             @RequestBody @Valid ScriptCreatDto scriptCreateDto) {
        log.info("userPk: " + principal.getId().toString());
        UUID id = principal.getId();
        scriptService.createScript(scriptCreateDto, id);
        return BaseResponse.success(
                SuccessCode.INSERT_SUCCESS,
                "스크립트 생성 성공"
        );
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
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
