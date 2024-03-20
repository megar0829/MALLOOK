package io.ssafy.mallook.domain.coupon.application;

import io.ssafy.mallook.domain.coupon.dto.response.CouponPageRes;
import io.ssafy.mallook.domain.coupon.dto.response.CouponRes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface CouponService {
    Slice<CouponRes> findMyCouponListFirst(Pageable pageable, UUID memberId);
    Slice<CouponRes> findMyCouponList(Pageable pageable, UUID memberId, Long cursor);
}
