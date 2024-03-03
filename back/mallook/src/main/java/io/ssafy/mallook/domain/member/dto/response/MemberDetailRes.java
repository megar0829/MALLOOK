package io.ssafy.mallook.domain.member.dto.response;

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
