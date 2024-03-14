package io.ssafy.mallook.domain.coupon.application;

import io.ssafy.mallook.domain.coupon.dao.CouponRepository;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.member_coupon.dao.MemberCouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private CouponService couponService;

    @BeforeEach
    void setUp(){
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
    }
//    Coupon

    @Test
    @DisplayName("내 쿠폰 조회")
    void findMyCouponList(){

    }

}