package io.ssafy.mallook.domain.coupon.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "쿠폰 삭제 요청 시")
public record CouponDeleteReq(
        @Schema(description="삭제하고자 하는 쿠폰의 memberCouponId 리스트")
        List<Long> memberCouponList
) {
}
