package io.ssafy.mallook;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MallookApplicationTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void main() {

    }

    @Test
    void checkMemberCount() {
        long memberCount = memberRepository.count();
        System.out.println("멤버 수: " + memberCount);
    }
}
