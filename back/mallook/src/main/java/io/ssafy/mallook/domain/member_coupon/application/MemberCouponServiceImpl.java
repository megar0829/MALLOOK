package io.ssafy.mallook.domain.member_coupon.application;

import io.ssafy.mallook.domain.coupon.entity.Coupon;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.member_coupon.dao.MemberCouponRepository;
import io.ssafy.mallook.domain.member_coupon.entity.MemberCoupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCouponServiceImpl implements MemberCouponService{
    private final MemberCouponRepository memberCouponRepository;

    @Override
    @Transactional
    public void saveMyCoupon(UUID memberId, Long couponId) {
        MemberCoupon myCoupon = MemberCoupon.builder()
                .member(new Member(memberId))
                .coupon(new Coupon(couponId))
                .build();
        memberCouponRepository.save(myCoupon);
    }

    @Override
    @Transactional
    public void deleteMyCoupon(List<Long> memberCouponIdList) {
        memberCouponRepository.deleteMyCoupon(memberCouponIdList);
    }
}
