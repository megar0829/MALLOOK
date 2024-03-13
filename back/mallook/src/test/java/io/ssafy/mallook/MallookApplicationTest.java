package io.ssafy.mallook;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.util.MemberFixtureFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = "dev")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MallookApplicationTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    void main() {
        int batchSize = 100000;
        for (int i = 0; i < 1000000; i += batchSize) {
            List<Member> tempMemberList = new ArrayList<>();
            for (int j = 0; j < batchSize; j++) {
                // Member 생성 로직
                Member member = MemberFixtureFactory.createMember();
                tempMemberList.add(member);
            }
            memberRepository.saveAll(tempMemberList);
            // 메모리 해제를 위해 tempMemberList 초기화
            tempMemberList.clear();
        }
    }
}