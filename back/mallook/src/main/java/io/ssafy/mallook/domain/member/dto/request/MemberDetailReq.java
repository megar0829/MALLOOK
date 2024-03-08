package io.ssafy.mallook.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Schema(description = "회원 정보 저장시 요청 DTO")
public record MemberDetailReq(
        @Schema(name="닉네임")
        @NotNull
        @Size(min = 2, max = 16)
        String nickname,
        @Schema(name="성별")
        @NotNull
        String gender,
        @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$")
        @Schema(name="생년월일")
        @NotNull
        String birth,
        @Schema(name="전화번호")
        @NotNull
        String phone,
        @Schema(name="시도")
        @NotNull
        String city,
        @Schema(name = "시군구")
        @NotNull
        String district,
        @Schema(name = "상세주소")
        @NotNull
        String address,
        @Schema(name = "우편번호")
        @NotNull
        String zipcode
) {
}
