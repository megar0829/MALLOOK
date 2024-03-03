package io.ssafy.mallook.domain.member_coupon.dao;

import io.ssafy.mallook.domain.member_coupon.entity.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {
}
