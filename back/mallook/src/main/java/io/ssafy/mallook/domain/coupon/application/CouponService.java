package io.ssafy.mallook.domain.coupon.application;

import io.ssafy.mallook.domain.coupon.dto.response.CouponPageRes;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CouponService {
    CouponPageRes findMyCouponList(Pageable pageable, UUID memberId);
}
