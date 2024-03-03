package io.ssafy.mallook.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "회원 정보 조회시 요청 DTO")
public record MemberDetailReq(
        String nickname,
        String gender,
        String birth,
        String phone,
        String city,
        String district,
        String address,
        String zipcode
) {
}
