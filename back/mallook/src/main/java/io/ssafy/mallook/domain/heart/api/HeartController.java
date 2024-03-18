package io.ssafy.mallook.domain.heart.api;

import io.ssafy.mallook.domain.heart.application.HeartService;
import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.domain.script.dto.response.ScriptListDto;
import io.ssafy.mallook.domain.style.dto.response.StyleListRes;
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
@RequestMapping("/api/hearts")
public class HeartController {

    private final HeartService heartService;

    @GetMapping("/scripts")
    public ResponseEntity<BaseResponse<Slice<ScriptListDto>>> getLikedScriptList(
            @AuthenticationPrincipal UserSecurityDTO principal,
            @PageableDefault(size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) Long cursor) {
        UUID id = principal.getId();
        cursor = cursor != null ? cursor : heartService.findMaxHeartId();

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                heartService.getLikeScriptList(cursor, id, pageable)
        );
    }

    @GetMapping("/styles")
    public ResponseEntity<BaseResponse<Slice<StyleListRes>>> getLikeStyleList(
            @AuthenticationPrincipal UserSecurityDTO principal,
            @PageableDefault(size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) Long cursor) {
        UUID id = principal.getId();
        cursor = cursor != null ? cursor : heartService.findMaxHeartId();

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                heartService.getLikeStyleList(cursor, id, pageable)
        );
    }

    @PostMapping("/scripts")
    public ResponseEntity<BaseResponse<String>> likeScript(@AuthenticationPrincipal UserSecurityDTO principal,
                                                           @RequestBody @Valid LikeDto likeDto) {
        UUID id = principal.getId();
        heartService.likeScript(id, likeDto);

        return BaseResponse.success(
                SuccessCode.INSERT_SUCCESS,
                "좋아요를 눌렀습니다."
        );
    }

    @PostMapping("/styles")
    public ResponseEntity<BaseResponse<String>> likeStyle(@AuthenticationPrincipal UserSecurityDTO principal,
                                                          @RequestBody @Valid LikeDto likeDto) {
        UUID id = principal.getId();
        heartService.likeStyle(id, likeDto);
        return BaseResponse.success(
                SuccessCode.INSERT_SUCCESS,
                "좋아요를 눌렀습니다."
        );
    }


    @DeleteMapping("/scripts")
    public ResponseEntity<BaseResponse<String>> unlikeScript(@AuthenticationPrincipal UserSecurityDTO principal,
                                                             @RequestBody @Valid LikeDto likeDto) {
        UUID id = principal.getId();
        heartService.unlikeScript(id, likeDto);

        return BaseResponse.success(
                SuccessCode.DELETE_SUCCESS,
                "좋아요를 취소했습니다."
        );
    }

    @DeleteMapping("/styles")
    public ResponseEntity<BaseResponse<String>> unlikeStyle(@AuthenticationPrincipal UserSecurityDTO principal,
                                                            @RequestBody @Valid LikeDto likeDto) {
        UUID id = principal.getId();
        heartService.unlikeStyle(id, likeDto);

        return BaseResponse.success(
                SuccessCode.DELETE_SUCCESS,
                "좋아요를 취소했습니다."
        );
    }
}
