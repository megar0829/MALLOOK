package io.ssafy.mallook.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
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
