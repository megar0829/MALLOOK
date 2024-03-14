package io.ssafy.mallook.domain.coupon.dao;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CouponRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @BeforeEach
    void setUp() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
    }
    void findAllByMemberIdTest(){


    }
}