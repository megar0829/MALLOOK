package io.ssafy.mallook.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "회원 정보 저장시 요청 DTO")
public record MemberNicknameReq(
        @Schema(description = "닉네임")
        @NotBlank(message = "닉네임은 공백일 수 없습니다.")
        @Size(min = 2, max = 16, message = "닉네임의 길이는 2~16자로 제한됩니다.")
        String nickname
) {
}
