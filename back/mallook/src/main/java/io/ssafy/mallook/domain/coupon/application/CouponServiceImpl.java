package io.ssafy.mallook.domain.coupon.application;

import io.ssafy.mallook.domain.coupon.dao.CouponRepository;
import io.ssafy.mallook.domain.coupon.dto.response.CouponPageRes;
import io.ssafy.mallook.domain.coupon.dto.response.CouponRes;
import io.ssafy.mallook.domain.member_coupon.dao.MemberCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{
    private final CouponRepository couponRepository;
    private final MemberCouponRepository memberCouponRepository;
    @Override
    public Slice<CouponRes> findMyCouponListFirst(Pageable pageable, UUID memberId) {
        Long maxId = memberCouponRepository.getMaxId(memberId);
        return couponRepository.findAllByMemberId(pageable, memberId, maxId+1);
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<CouponRes> findMyCouponList(Pageable pageable, UUID memberId, Long cursor) {
        return couponRepository.findAllByMemberId(pageable, memberId, cursor + 1);
    }

}
