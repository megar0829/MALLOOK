package io.ssafy.mallook.domain.coupon.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "쿠폰 정보 요청시 페이지 정보포함 응답 DTO")
public record CouponPageRes(
        @Schema(description="쿠폰 정보 리스트")
        List<CouponRes> couponReslList,
        @Schema(description="전체 페이지")
        int totalPage,
        @Schema(description = "현재 페이지")
        int currentPage
) {
}
