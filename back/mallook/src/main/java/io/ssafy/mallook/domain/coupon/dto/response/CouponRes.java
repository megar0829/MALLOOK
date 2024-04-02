package io.ssafy.mallook.domain.coupon.dto.response;

import io.ssafy.mallook.domain.coupon.entity.CouponType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;


@Schema(description = "쿠폰 조회시 응답 DTO")
public record CouponRes(
        @Schema(description = "내 쿠폰 id")
        Long myCouponId,
        @Schema(description = "쿠폰명")
        String name,
        @Schema(description = "쿠폰 타입(정액쿠폰 또는 정률쿠폰)")
        CouponType type,
        @Schema(description = "할인 금액 또는 할인비율")
        String amount,
        @Schema(description = "만료일")
        Object expiredTime
) {
}
