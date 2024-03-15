package io.ssafy.mallook.domain.coupon.application;

import io.ssafy.mallook.domain.coupon.dao.CouponRepository;
import io.ssafy.mallook.domain.coupon.dto.response.CouponPageRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{
    private final CouponRepository couponRepository;
    @Override
    @Transactional(readOnly = true)
    public CouponPageRes findMyCouponList(Pageable pageable, UUID memberId) {
        var result = couponRepository.findAllByMemberId(pageable, memberId);
        return new CouponPageRes(result.getContent(), result.getTotalPages(), result.getNumber());
    }

}
