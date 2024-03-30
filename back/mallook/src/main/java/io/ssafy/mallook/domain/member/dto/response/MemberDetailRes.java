package io.ssafy.mallook.domain.member.dto.response;


import io.ssafy.mallook.domain.member.entity.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;
@Builder
@Schema(description = "회원 정보 조회시 응답 DTO")
public record MemberDetailRes(
        @Schema(description = "닉네임")
        String nickname,
        @Schema(description = "닉네임 태그")
        String nicknameTag,
        @Schema(description = "생년월일")
        String birth,
        @Schema(description = "성별")
        String gender,
        @Schema(description = "전화번호")
        String phone,
        @Schema(description = "회원등급")
        String grade,
        @Schema(description = "경험치 범위")
        List<Integer> expRange,
        @Schema(description = "포인트")
        Long point,
        @Schema(description = "경험치")
        Long exp,
        @Schema(description = "주소")
        Address address,
        @Schema(description = "보유한 쿠폰수")
        Long memberCoupon,
        @Schema(description = "발급가능한 쿠폰 수")
        Long coupon,
        @Schema(description = "장바구니 내 상품 수")
        Long cartProduct,
        @Schema(description = "주문 수")
        Long orders
) {
}
