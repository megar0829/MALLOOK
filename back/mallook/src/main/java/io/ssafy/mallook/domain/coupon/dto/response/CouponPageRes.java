package io.ssafy.mallook.domain.coupon.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "쿠폰 정보 요청시 페이지 정보포함 응답 DTO")
public record CouponPageRes(
        @Schema(description = "쿠폰 정보 리스트")
        List<MemberCouponRes> content,
        @Schema(description = "다음 커서 위치")
        Long nextCursor
) {
}
