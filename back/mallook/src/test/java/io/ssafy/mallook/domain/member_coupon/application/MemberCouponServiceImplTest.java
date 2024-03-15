package io.ssafy.mallook.domain.member_coupon.application;

import io.ssafy.mallook.domain.coupon.dao.CouponRepository;
import io.ssafy.mallook.domain.coupon.entity.Coupon;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.member_coupon.dao.MemberCouponRepository;
import io.ssafy.mallook.domain.member_coupon.entity.MemberCoupon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MemberCouponServiceImplTest {

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private MemberCouponRepository memberCouponRepository;
    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private MemberCouponServiceImpl memberCouponService;

    private Member member;
    private Coupon coupon;
    @BeforeEach
    void setUp(){
        memberRepository.save(member);
        couponRepository.save(coupon);
        memberCouponRepository.save(buildMemberCoupon(member, coupon));
    }
    MemberCoupon buildMemberCoupon(Member member, Coupon coupon){
        return MemberCoupon.builder()
                .member(member)
                .coupon(coupon)
                .build();
    }
    @Test
    @DisplayName("나에게 등록된 쿠폰 삭제")
    void deleteMyCoupon(){
        // Given
        List<Long> memberCouponList = new ArrayList<>();

        // When
        memberCouponRepository.deleteMyCoupon(memberCouponList);

        // Then
        Mockito.verify(memberCouponRepository, Mockito.times(1)).
    }

}