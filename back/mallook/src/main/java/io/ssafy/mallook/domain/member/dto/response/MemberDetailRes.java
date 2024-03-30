package io.ssafy.mallook.domain.member.dto.response;


import io.ssafy.mallook.domain.member.entity.Address;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "회원 정보 조회시 응답 DTO")
public record MemberDetailRes(
        @Schema(description = "닉네임")
        String nickname,
        @Schema(description = "생년월일")
        String birth,
        @Schema(description = "성별")
        String gender,
        @Schema(description = "전화번호")
        String phone,
        @Schema(description = "포인트")
        Long point,
        @Schema(description = "경험치")
        Long exp,
        @Schema(description = "경험치 범위")
        List<Integer> expRange,
        @Schema(description = "주소")
        Address address
) {
}
