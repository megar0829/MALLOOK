package io.ssafy.mallook.domain.member.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "회원 정보 조회시 응답 DTO")
public record MemberDetailRes(
        @Schema(name="닉네임")
        String nickname,
        @Schema(name="생년월일")
        String birth,
        @Schema(name="성별")
        String gender,
        @Schema(name="전화번호")
        String phone,
        @Schema(name="포인트")
        Long point,
        @Schema(name="경험치")
        Long exp,
        @Schema(name="시도")
        String city,
        @Schema(name = "시군구")
        String district,
        @Schema(name = "상세주소")
        String address,
        @Schema(name = "우편번호")
        String zipcode
) {
}
