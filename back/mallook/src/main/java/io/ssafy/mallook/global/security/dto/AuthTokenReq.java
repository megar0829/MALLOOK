package io.ssafy.mallook.global.security.dto;


import io.ssafy.mallook.global.security.validator.Token;

public record AuthTokenReq(
        @Token String refreshToken
) {
}
