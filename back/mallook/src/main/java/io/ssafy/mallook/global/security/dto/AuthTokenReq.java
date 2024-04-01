package io.ssafy.mallook.global.security.dto;


import jakarta.validation.constraints.NotBlank;

public record AuthTokenReq(
        @NotBlank String refreshToken
) {
}
