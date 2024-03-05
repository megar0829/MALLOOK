package io.ssafy.mallook.global.security.dto;

public record TokenDto(
        String accessToken,
        String refreshToken
) {
}