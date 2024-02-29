package io.ssafy.mallook.global.security.dto;


import io.ssafy.mallook.global.security.filter.Token;

public record AuthTokenReq(
        @Token String refreshToken
) {
}
