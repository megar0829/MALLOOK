package io.ssafy.mallook.domain.heart.script_heart.dao;

import io.ssafy.mallook.domain.heart.script_heart.entity.ScriptHeart;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.entity.Script;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = "test")
class ScriptHeartRepositoryTest {

    @Autowired
    private ScriptHeartRepository scriptHeartRepository;

    @Autowired
    private ScriptRepository scriptRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager entityManager;

    private Member member;
    private Member member2;
    private Script script;

    @BeforeEach
    void set() {
        member = Mockito.mock(Member.class);
        member2 = Mockito.mock(Member.class);
        memberRepository.save(member);
        memberRepository.save(member2);
        script = buildScript(member);
        scriptRepository.save(script);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    void findByIdLessThanAndMemberOrderByIdDesc() {
        ScriptHeart scriptHeart1 = ScriptHeart.builder()
                .member(member)
                .script(script)
                .build();
        scriptHeartRepository.save(scriptHeart1);

        ScriptHeart scriptHeart2 = ScriptHeart.builder()
                .member(member)
                .script(script)
                .build();
        scriptHeartRepository.save(scriptHeart2);

        Long searchId = scriptHeart2.getId() + 1;
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));

        // When
        Slice<ScriptHeart> resultSlice = scriptHeartRepository.findByIdLessThanAndMemberOrderByIdDesc(searchId, member, pageRequest);

        // Then
        assertThat(resultSlice.hasContent()).isTrue();
        assertThat(resultSlice.getContent()).containsExactly(scriptHeart2, scriptHeart1);
    }

    @Test
    void findByMemberAndScript() {
        ScriptHeart scriptHeart = ScriptHeart.builder()
                .member(member)
                .script(script)
                .build();
        scriptHeartRepository.save(scriptHeart);

        // When
        Optional<ScriptHeart> found = scriptHeartRepository.findByMemberAndScript(member, script);

        // Then
        assertThat(found).isPresent();
        assertThat(found.get()).isEqualTo(scriptHeart);
    }

    @Test
    void findMaxHeartId() {
        ScriptHeart scriptHeart1 = ScriptHeart.builder()
                .member(member)
                .script(script)
                .build();
        scriptHeartRepository.save(scriptHeart1);

        ScriptHeart scriptHeart2 = ScriptHeart.builder()
                .member(member2)
                .script(script)
                .build();
        scriptHeartRepository.save(scriptHeart2);
        entityManager.flush();
        entityManager.clear();

        // When
        Long maxId = scriptHeartRepository.findMaxHeartId();

        // Then
        assertThat(maxId).isNotNull();
        assertThat(maxId).isEqualTo(scriptHeart2.getId());
    }

    private Script buildScript(Member member) {
        return Script.builder()
                .name("테스트용 스크립트")
                .member(member)
                .heartCount(0)
                .totalLike(0)
                .build();
    }
}