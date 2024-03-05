package io.ssafy.mallook.domain.heart.api;

import io.ssafy.mallook.domain.heart.application.HeartService;
import io.ssafy.mallook.domain.heart.dto.request.LikeDto;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hearts")
public class HeartController {

    private final HeartService heartService;

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
}
