package io.ssafy.mallook.domain.coupon.dao;

import io.ssafy.mallook.domain.coupon.dto.response.CouponRes;
import io.ssafy.mallook.domain.coupon.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Query("""
        SELECT new io.ssafy.mallook.domain.coupon.dto.response.CouponRes(
                mc.id, c.name, c.type, c.amount, c.expiredTime
            )
            FROM MemberCoupon mc
            JOIN mc.coupon c
            WHERE mc.member.id = :memberId AND mc.status = true
    """)
    Page<CouponRes> findAllByMemberId(Pageable pageable, @Param("memberId") UUID memberId);
}
