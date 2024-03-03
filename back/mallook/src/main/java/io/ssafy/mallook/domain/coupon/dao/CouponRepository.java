package io.ssafy.mallook.domain.coupon.dao;

import io.ssafy.mallook.domain.coupon.dto.response.CouponRes;
import io.ssafy.mallook.domain.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Query("""
        SELECT new io.ssafy.mallook.domain.coupon.dto.response.CouponRes(
            c.name, c.type, c.amount, c.expiredTime
        )
        FROM Coupon c
        JOIN MemberCoupon mc
        ON mc.member = :memberId
    """)
    List<CouponRes> findAllByMemberId(UUID memberId);
}
