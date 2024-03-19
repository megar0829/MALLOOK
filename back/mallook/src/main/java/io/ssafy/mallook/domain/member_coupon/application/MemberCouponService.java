package io.ssafy.mallook.domain.member_coupon.application;

import java.util.UUID;
import java.util.List;

public interface MemberCouponService {
    void saveMyCoupon(UUID memberId, Long couponId);
    void deleteMyCoupon(List<Long> memberCouponIdList);
}
