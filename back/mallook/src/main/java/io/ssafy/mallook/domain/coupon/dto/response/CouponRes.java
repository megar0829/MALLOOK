package io.ssafy.mallook.domain.coupon.dto.response;

import io.ssafy.mallook.domain.coupon.entity.CouponType;

public record CouponRes(
        Long id,
        String name,
        Object createdAt,
        Object expiredTime,
        CouponType couponType
) {
}
