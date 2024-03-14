package io.ssafy.mallook.domain.style.dao;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.style.entity.Style;
import io.ssafy.mallook.domain.style_product.dao.StyleProductRepository;
import io.ssafy.mallook.domain.style_product.entity.StyleProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(profiles = "dev")
class StyleRepositoryTest {

    @Autowired
    StyleRepository styleRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    StyleProductRepository styleProductRepository;

    @BeforeEach
    void setUp() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
    }

    private Style buildStyle(List<StyleProduct> styleProductList, String name, Member member) {
        return Style.builder()
                .styleProductList(styleProductList)
                .name(name)
                .member(member)
                .build();
    }
    private Member buildMember(String nickname) {
        return Member.builder()
                .nickname(nickname)
                .build();
    }

    @Test
    @DisplayName("페이지를 적용하여 스타일 전체 조회")
    void findAllTest() {
        List<Style> styleList = new ArrayList<>();
        for (int i = 0; i < 20; i ++) {
            // 스타일에 속하는 상품 리스트
            List<StyleProduct> spList = new ArrayList<>();
            for (int j = 0 ; j < 2; j ++) {
                StyleProduct sp = Mockito.mock(StyleProduct.class);
                styleProductRepository.save(sp);
                spList.add(sp);
            }
            // 스타일 작성 멤버
            Member member = Mockito.mock(Member.class);
            memberRepository.save(member);
            // 스타일
            Style style = buildStyle(spList, "테스트용 제목", member);
            var rs = styleRepository.save(style);
            styleList.add(rs);
        }
        Pageable pageable = PageRequest.of(0, 20);
        var result = styleRepository.findAll(pageable);
        assertThat(Objects.nonNull(result)).isTrue();
        for (var a : styleList) {
            assertThat(result.getContent().contains(a)).isTrue();
        }
    }
    @Test
    @DisplayName("내가 등록한 스타일 삭제")
    void deleteMyStyleTest() {
        List<Long> deleteList = new ArrayList<>();
        // 스타일 작성 멤버
        Member member = buildMember("테스트용 닉네임");
        memberRepository.save(member);
        for (int i = 0; i < 20; i ++) {
            // 스타일에 속하는 상품 리스트
            List<StyleProduct> spList = new ArrayList<>();
            for (int j = 0 ; j < 2; j ++) {
                StyleProduct sp = Mockito.mock(StyleProduct.class);
                styleProductRepository.save(sp);
                spList.add(sp);
            }
            // 스타일
            Style style = buildStyle(spList, "테스트용 제목", member);
            var rs = styleRepository.save(style);
            deleteList.add(rs.getId());
        }
        styleRepository.deleteMyStyle(member.getId(), deleteList);
        for (var a : deleteList) {
            assertThat(styleRepository.findById(a).isPresent()).isFalse();
        }

    }
}