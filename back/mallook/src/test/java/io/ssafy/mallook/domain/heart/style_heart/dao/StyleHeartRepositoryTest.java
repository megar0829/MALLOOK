package io.ssafy.mallook.domain.heart.style_heart.dao;

import io.ssafy.mallook.domain.heart.style_heart.entity.StyleHeart;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.entity.Style;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = "test")
class StyleHeartRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StyleRepository styleRepository;

    @Autowired
    private StyleHeartRepository styleHeartRepository;

    @Autowired
    private EntityManager entityManager;

    private Member member;
    private Member member2;
    private Style style;

    @BeforeEach
    void set() {
        member = Mockito.mock(Member.class);
        member2 = Mockito.mock(Member.class);
        memberRepository.save(member);
        memberRepository.save(member2);
        style = buildStyle(member);
        styleRepository.save(style);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    void findByIdLessThanAndMemberOrderByIdDesc() {
    }

    @Test
    void findByMemberAndStyle() {
    }

    @Test
    void findMaxHeartId() {
        StyleHeart styleHeart1 = StyleHeart.builder()
                .member(member)
                .style(style)
                .build();
        styleHeartRepository.save(styleHeart1);

        StyleHeart styleHeart2 = StyleHeart.builder()
                .member(member2)
                .style(style)
                .build();
        styleHeartRepository.save(styleHeart2);
        entityManager.flush();
        entityManager.clear();

        Long maxId = styleHeartRepository.findMaxHeartId();
        assertThat(maxId) .isNotNull();
        assertThat(maxId).isEqualTo(styleHeart2.getId());
    }

    private Style buildStyle(Member member) {
        return Style.builder()
                .name("테스트용 제목")
                .member(member)
                .heartCount(0L)
                .totalLike(0)
                .build();
    }
}