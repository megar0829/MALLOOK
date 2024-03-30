package io.ssafy.mallook.global.security.api;

import io.ssafy.mallook.domain.member.entity.SocialType;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.ssafy.mallook.global.security.application.AuthService;
import io.ssafy.mallook.global.security.dto.AuthTokenReq;
import io.ssafy.mallook.global.security.dto.KakaoAuthTokenReq;
import io.ssafy.mallook.global.security.dto.KakaoAuthTokenRes;
import io.ssafy.mallook.global.security.dto.TokenDto;
import io.ssafy.mallook.global.security.exception.RefreshTokenException;
import io.ssafy.mallook.global.security.service.CustomOAuth2UserService;
import io.ssafy.mallook.global.security.service.JwtService;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "인증", description = "토큰 인증 컨트롤러")
public class AuthController {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final AuthService authService;
    private final JwtService jwtService;

    @Operation(summary = "모바일 카카오 소셜로그인 확인")
    @PostMapping("/login/kakao")
    public ResponseEntity<BaseResponse<TokenDto>> issueTokenToMobileLoginByKakao(@RequestBody KakaoAuthTokenReq token) {
        KakaoAuthTokenRes kakaoAuthTokenRes = authService.verifyKakaoToken(token.accessToken());
        UserSecurityDTO userSecurityDTO = customOAuth2UserService.getMobileSecurityDto(SocialType.KAKAO, kakaoAuthTokenRes);

        TokenDto tokenDto = new TokenDto(
                jwtService.createAccessToken(userSecurityDTO),
                jwtService.createRefreshToken(userSecurityDTO),
                userSecurityDTO.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
        );

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                tokenDto
        );
    }

    @Operation(summary = "액세스 토큰 재발급 요청하기", description = "액세스 토큰 없거나 만료됐으면 재발급 요청하기")
    @PostMapping("/token/refresh")
    public ResponseEntity<BaseResponse<TokenDto>> rotateJwtTokensRequest(
            @Valid @NotNull @RequestBody AuthTokenReq authTokenReq) throws RefreshTokenException {
        TokenDto tokenDto = jwtService.rotateJwtTokens(authTokenReq.refreshToken());

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                tokenDto
        );
    }
}
