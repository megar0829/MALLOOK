package io.ssafy.mallook.domain.coupon.dto.response;

import io.ssafy.mallook.domain.cart.dto.response.CartDetailRes;

import java.util.List;

public record CouponPageRes(
        List<CouponRes> couponReslList,
        int totalPage,
        int currentPage
) {
}
