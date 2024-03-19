package io.ssafy.mallook.domain.heart.dao;

import io.ssafy.mallook.domain.heart.entity.Heart;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.entity.Script;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.entity.Style;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles(profiles = {"dev", "local"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HeartRepositoryTest {

    @Autowired
    private HeartRepository heartRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StyleRepository styleRepository;

    @Autowired
    private ScriptRepository scriptRepository;

    @Autowired
    private EntityManager entityManager;

    private Script script;
    private Style style;
    private Member member;

    @BeforeEach
    void setUp() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
        style = buildStyle(member);
        styleRepository.save(style);
        script = buildScript(member);
        scriptRepository.save(script);
        entityManager.flush();
        entityManager.clear();
    }

    private Style buildStyle(Member member) {
        return Style.builder()
                .name("테스트 스타일")
                .member(member)
                .heartCount(0L)
                .build();
    }

    private Script buildScript(Member member) {
        return Script.builder()
                .name("테스트 스크립트")
                .member(member)
                .heartCount(0)
                .build();
    }

    @Test
    void findAllByMemberAndScriptIsNotNull() {
        PageRequest pageable = PageRequest.of(0, 2);
        Heart heart = Heart.builder()
                .member(member)
                .script(script)
                .build();
        heartRepository.save(heart);
        Page<Heart> page = heartRepository.findAllByMemberAndScriptIsNotNull(member, pageable);
        assertThat(page).isNotNull();
        assertThat(page.getContent()).contains(heart);
    }

    @Test
    void findAllByMemberAndStyleIsNotNull() {
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