package io.ssafy.mallook.global.security.application;

import io.ssafy.mallook.global.security.dto.KakaoAuthTokenRes;

public interface AuthService {
    KakaoAuthTokenRes verifyKakaoToken(String accessToken);
}
