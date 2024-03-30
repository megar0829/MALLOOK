package io.ssafy.mallook.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "랜덤 닉네임 조회시 응답 DTO")
public record NicknameRes(
        @Schema(description = "닉네임")
        String nickname
) {
}
