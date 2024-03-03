package io.ssafy.mallook.domain.coupon.dto.response;

import io.ssafy.mallook.domain.coupon.entity.CouponType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;


@Schema(description = "회원 정보 조회시 응답 DTO")
public record CouponRes(
        @Schema(name="내 쿠폰 id")
        Long myCouponId,
        @Schema(name="쿠폰명")
        String name,
        @Schema(name="쿠폰 타입(정액쿠폰 또는 정률쿠폰)")
        CouponType type,
        @Schema(name="할인 금액 또는 할인비율")
        String amount,
        @Schema(name="만료일")
        LocalDateTime expiredTime
) {
}
