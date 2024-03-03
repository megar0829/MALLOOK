package io.ssafy.mallook.domain.coupon.dto.response;

import io.ssafy.mallook.domain.coupon.entity.CouponType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;


public record CouponRes(
        Long myCouponId,
        String name,
        CouponType type,
        String amount,
        LocalDateTime expiredTime
) {
}
