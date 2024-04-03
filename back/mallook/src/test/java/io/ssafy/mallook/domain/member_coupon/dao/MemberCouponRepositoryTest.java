package io.ssafy.mallook.domain.member_coupon.dao;

import io.ssafy.mallook.domain.coupon.dao.CouponRepository;
import io.ssafy.mallook.domain.coupon.entity.Coupon;
import io.ssafy.mallook.domain.coupon.entity.CouponType;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.member_coupon.entity.MemberCoupon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles(profiles = "test")
class MemberCouponRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    MemberCouponRepository memberCouponRepository;

    @BeforeEach
    void setUp() {
    }

    private Member buildMember(String nickname) {
        return Member.builder()
                .nickname(nickname)
                .build();
    }

    private MemberCoupon buildMemberCoupon(Coupon coupon, Member member) {
        return MemberCoupon.builder()
                .coupon(coupon)
                .member(member)
                .build();
    }

    private Coupon buildCoupon() {
        return Coupon.builder()
                .name("test 쿠폰")
                .type(CouponType.MONEY)
                .amount(2L)
                .expiredTime(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("나에게 등록된 쿠폰 삭제 테스트")
    void deleteMyCouponTest() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
        List<Long> deleteList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Coupon coupon = buildCoupon();
            couponRepository.save(coupon);
            var memberCoupon = buildMemberCoupon(coupon, member);
            var rs = memberCouponRepository.save(memberCoupon);
            deleteList.add(rs.getId());
        }
        memberCouponRepository.deleteMyCoupon(deleteList);
        for (var i : deleteList) {
            assertThat(memberCouponRepository.findById(i).isPresent()).isFalse();
        }
    }

    @Test
    @DisplayName("나에게 등록된 쿠폰인지 확인")
    void existsByIdAndMember_IdTest() {
        var mem1 = memberRepository.save(buildMember("테스트1"));
        var mem2 = memberRepository.save(buildMember("테스트2"));

        Coupon coupon1 = buildCoupon();
        couponRepository.save(coupon1);
        Coupon coupon2 = buildCoupon();
        couponRepository.save(coupon2);

        var rs1 = memberCouponRepository.save(buildMemberCoupon(coupon1, mem1));
        memberCouponRepository.save(buildMemberCoupon(coupon2, mem2));

        var result1 = memberCouponRepository.existsByIdAndMember_Id(rs1.getId(), mem1.getId());
        var result2 = memberCouponRepository.existsByIdAndMember_Id(rs1.getId(), mem2.getId());

        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
    }
}