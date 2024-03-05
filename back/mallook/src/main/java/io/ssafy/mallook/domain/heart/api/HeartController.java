package io.ssafy.mallook.domain.heart.api;

import io.ssafy.mallook.domain.heart.application.HeartService;
import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hearts")
public class HeartController {

    private final HeartService heartService;

    @GetMapping("/scripts")
    public ResponseEntity<BaseResponse<Page<ScriptListDto>>> getLikedScriptList(@AuthenticationPrincipal UserSecurityDTO principal,
                                                                                @PageableDefault(size = 20,
                                                                   sort = "createdAt",
                                                                   direction = Sort.Direction.DESC) Pageable pageable) {
        UUID id = principal.getId();

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                heartService.getLikeScriptList(id, pageable)
        );
    }

    @PostMapping("/script")
    public ResponseEntity<BaseResponse<String>> likeScript(@AuthenticationPrincipal UserSecurityDTO principal,
                                                           @RequestBody @Valid LikeDto likeDto) {
        UUID id = principal.getId();
        heartService.likeScript(id, likeDto);

        return BaseResponse.success(
                SuccessCode.INSERT_SUCCESS,
                "좋아요를 눌렀습니다."
        );
    }

    @DeleteMapping("/script")
    public ResponseEntity<BaseResponse<String>> unlikeScript(@AuthenticationPrincipal UserSecurityDTO principal,
                                                             @RequestBody @Valid LikeDto likeDto) {
        UUID id = principal.getId();
        heartService.unlikeScript(id, likeDto);

        return BaseResponse.success(
                SuccessCode.DELETE_SUCCESS,
                "좋아요를 취소했습니다."
        );
    }
}
