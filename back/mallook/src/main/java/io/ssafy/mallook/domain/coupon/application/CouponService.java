package io.ssafy.mallook.domain.coupon.application;

import io.ssafy.mallook.domain.coupon.dto.response.CouponRes;
import io.ssafy.mallook.domain.coupon.dto.response.MemberCouponRes;
import io.ssafy.mallook.domain.coupon.entity.Coupon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface CouponService {
    Slice<CouponRes> findCouponListFirst(Pageable pageable);
    Slice<CouponRes> findCouponList(Pageable pageable, Long cursor);
    Slice<MemberCouponRes> findMyCouponListFirst(Pageable pageable, UUID memberId);

    Slice<MemberCouponRes> findMyCouponList(Pageable pageable, UUID memberId, Long cursor);

    void saveNewCoupon();

    void decreaseCoupon(Long couponId, UUID memberId);
}
