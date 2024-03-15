package io.ssafy.mallook.domain.coupon.application;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CouponServiceImplTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private CouponRepository couponRepository;
    @Mock
    private MemberCouponRepository memberCouponRepository;
    @InjectMocks
    private CouponServiceImpl couponService;

    private MemberCoupon buildMemberCoupon(Member member, Coupon coupon){
        return MemberCoupon.builder()
                .member(member)
                .coupon(coupon)
                .build();
    }
    private Member member;
    private Coupon coupon;

    @BeforeEach
    void setUp() {
        member = Mockito.mock(Member.class);
        memberRepository.save(member);
        coupon = Mockito.mock(Coupon.class);
        couponRepository.save(coupon);
        buildMemberCoupon(member, coupon);
    }

    @Test
    @DisplayName("내 쿠폰 조회 테스트")
    void findMyCouponList(){
        // given
        Pageable pageable = PageRequest.of(0, 2);
        couponRepository.findAllByMemberId(pageable, member.getId());
        // when


    }

}