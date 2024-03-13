package io.ssafy.mallook.domain.heart.dao;

import io.ssafy.mallook.domain.heart.entity.Heart;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.entity.Style;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = "dev")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HeartRepositoryTest {

    @Autowired
    private HeartRepository heartRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StyleRepository styleRepository;

    private Style style;
    private Member member;

    @BeforeEach
    void setUp() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
        style = buildStyle(member);
        styleRepository.save(style);
    }

    private Style buildStyle(Member member) {
        return Style.builder()
                .name("테스트 스타일")
                .member(member)
                .heartCount(0L)
                .build();
    }

    @Test
    void findAllByMember() {
        PageRequest pageable = PageRequest.of(0, 2);
        Heart heart = Heart.builder()
                .member(member)
                .style(style)
                .build();
        heartRepository.save(heart);
        Page<Heart> page = heartRepository.findAllByMemberAndStyleIsNotNull(member, pageable);
        assertThat(page).isNotNull();
        assertThat(page.getContent()).contains(heart);
    }

    @Test
    void findByMemberAndScript() {

    }

    @Test
    void findByMemberAndStyle() {
        Heart heart = Heart.builder()
                .member(member)
                .style(style)
                .build();
        heartRepository.save(heart);
        Heart getHeart = heartRepository.findByMemberAndStyle(member, style)
                .get();
        assertThat(heart.getId()).isEqualTo(getHeart.getId());
    }
}