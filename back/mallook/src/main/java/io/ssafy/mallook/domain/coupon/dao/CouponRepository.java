package io.ssafy.mallook.domain.coupon.dao;

import io.ssafy.mallook.domain.coupon.dto.response.CouponRes;
import io.ssafy.mallook.domain.coupon.dto.response.MemberCouponRes;
import io.ssafy.mallook.domain.coupon.entity.Coupon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("""
        SELECT new io.ssafy.mallook.domain.coupon.dto.response.CouponRes(
            c.id, c.name, function('DATE_FORMAT', c.createdAt, '%Y-%m-%d %H:%m:%s'), function('DATE_FORMAT', c.expiredTime, '%Y-%m-%d %H:%m:%s'), c.type
        )
        FROM Coupon c
        left join MemberCoupon mc
        on c.id = mc.coupon.id
        WHERE (mc.member.id != :memberId or mc.member is null) and c.id < :cursor
       """)
    Slice<CouponRes> findCouponBy(Pageable pageable, @Param("cursor") Long cursor, @Param("memberId") UUID memberId);
    @Query("""
                SELECT new io.ssafy.mallook.domain.coupon.dto.response.MemberCouponRes(
                        mc.id, c.name, c.type, c.amount, FUNCTION('DATE_FORMAT', c.expiredTime, '%Y-%m-%d %H:%m:%s')
                    )
                    FROM MemberCoupon mc
                    JOIN mc.coupon c
                    WHERE mc.member.id = :memberId AND mc.id < :cursor
            """)
    Slice<MemberCouponRes> findAllByMemberId(Pageable pageable, @Param("memberId") UUID memberId, @Param("cursor") Long cursor);

    @Query("select max(c.id) from Coupon c")
    Long getMaxId();
    Long countBy();

}
