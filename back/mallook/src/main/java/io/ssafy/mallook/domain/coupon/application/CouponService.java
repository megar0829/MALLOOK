package io.ssafy.mallook.domain.coupon.application;

import io.ssafy.mallook.domain.coupon.dto.response.CouponRes;

import java.util.List;
import java.util.UUID;

public interface CouponService {
    List<CouponRes> findMyCouponList(UUID memberId);
}
