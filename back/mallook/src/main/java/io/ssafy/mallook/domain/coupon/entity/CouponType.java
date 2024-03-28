package io.ssafy.mallook.domain.coupon.entity;

public enum CouponType {
    MONEY("정액할인"),
    RATIO("정률할인"),
    ;
    final String korean;

    CouponType(String korean) {
        this.korean = korean;
    }
}
