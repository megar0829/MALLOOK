package io.ssafy.mallook.domain.member_coupon.application;

import java.util.UUID;

public interface MemberCouponService {
    void saveMyCoupon(UUID memberId, Long couponId);
}
