package io.ssafy.mallook.domain.member.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "회원 정보 조회시 응답 DTO")
public record MemberDetailRes(
        String nickname,
        String birth,
        String gender,
        String phone,
        Long point,
        Long exp,
        String city,
        String district,
        String address,
        String zipcode
) {
}
