package io.ssafy.mallook.domain.script.dao;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.entity.Script;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ScriptRepositoryTest {

    @Autowired
    private ScriptRepository scriptRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Script script;

    @BeforeEach
    void setUp() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
        script = buildScript(member);
    }

    private Script buildScript(Member member) {
        return Script.builder()
                .name("테스트용 스크립트")
                .member(member)
                .heartCount(0)
                .build();
    }

    @Test
    @DisplayName("멤버의 활성화된 스크립트 조회 테스트")
    void findAllByMember() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
        PageRequest pageable = PageRequest.of(0, 2);

        Script script1 = buildScript(member);
        Script script2 = buildScript(member);
        scriptRepository.save(script1);
        scriptRepository.save(script2);

        Page<Script> scriptPage = scriptRepository.findAllByMember(member, pageable);
        assertThat(scriptPage).isNotNull(); // 결과가 null이 아닌지 확인
        assertThat(scriptPage.getContent()).contains(script1, script2); // 스크립트가 올바르게 조회되었는지 확인
    }

    @Test
    @DisplayName("스크립트 삭제 테스트")
    void deleteScript() {
        // 스크립트 생성 및 저장
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
        List<Long> deleteList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Script script = buildScript(member);
            scriptRepository.save(script);
            deleteList.add(script.getId());
            System.out.println(script.getId());
        }

        scriptRepository.deleteScript(deleteList);

        for (Long id : deleteList) {
            Optional<Script> optionalScript = scriptRepository.findById(id);
            assertThat(optionalScript.isPresent()).isFalse(); // 저장되었는지 확인
        }
    }
}