package io.ssafy.mallook.domain.heart.script_heart.api;

import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.heart.script_heart.application.ScriptHeartService;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hearts/scripts")
public class ScriptHeartController {

    private final ScriptHeartService scriptHeartService;

    @GetMapping
    public ResponseEntity<BaseResponse<Slice<ScriptListDto>>> getLikedScriptList(
            @AuthenticationPrincipal UserSecurityDTO principal,
            @PageableDefault(size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) Long cursor) {
        UUID id = principal.getId();
        cursor = cursor != null ? cursor : scriptHeartService.findMaxHeartId();

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                scriptHeartService.getLikeScriptList(cursor, id, pageable)
        );
    }

    @PostMapping
    public ResponseEntity<BaseResponse<String>> likeScript(@AuthenticationPrincipal UserSecurityDTO principal,
                                                           @RequestBody @Valid LikeDto likeDto) {
        UUID id = principal.getId();
        scriptHeartService.likeScript(id, likeDto);

        return BaseResponse.success(
                SuccessCode.INSERT_SUCCESS,
                "좋아요를 눌렀습니다."
        );
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse<String>> unlikeScript(@AuthenticationPrincipal UserSecurityDTO principal,
                                                             @RequestBody @Valid LikeDto likeDto) {
        UUID id = principal.getId();
        scriptHeartService.unlikeScript(id, likeDto);

        return BaseResponse.success(
                SuccessCode.DELETE_SUCCESS,
                "좋아요를 취소했습니다."
        );
    }
}
