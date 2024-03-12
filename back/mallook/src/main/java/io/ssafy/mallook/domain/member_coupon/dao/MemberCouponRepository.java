package io.ssafy.mallook.domain.member_coupon.dao;

import io.ssafy.mallook.domain.member_coupon.entity.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import java.util.UUID;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {
    @Modifying(clearAutomatically = true)
    @Query("""
        update MemberCoupon mc set mc.status = false
        where mc.id in :memberCouponList and mc.status = true
        """)
    void deleteMyCoupon(@Param("memberCouponList") List<Long> memberCouponList);
    boolean existsByIdAndMember_Id(Long id, UUID memberId);
}
