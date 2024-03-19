package io.ssafy.mallook.domain.style_product.dao;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.product.dao.ProductRepository;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.entity.Style;
import io.ssafy.mallook.domain.style_product.entity.StyleProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(profiles = {"dev", "local"})
class StyleProductRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    StyleRepository styleRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    StyleProductRepository styleProductRepository;

    @BeforeEach
    void setUp() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
    }

    private StyleProduct buildStyleProduct(Product product, Style style) {
        return StyleProduct.builder()
                .product(product)
                .style(style)
                .build();
    }
    private Style buildStyle(String name, Member member) {
        return Style.builder()
                .name(name)
                .member(member)
                .build();
    }

    @Test
    @DisplayName("내가 저장한 스타일 내 상품 제거")
    void deleteMyStyleProductTest() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
        Style style = buildStyle("테스트용 제목", member);
        styleRepository.save(style);
        List<Long> deleteList = new ArrayList<>();
        for (int i =0 ; i < 3; i ++) {
            Product pd = Mockito.mock(Product.class);
            productRepository.save(pd);
            var rs = styleProductRepository.save(buildStyleProduct(pd, style));
            deleteList.add(rs.getId());
        }
        styleProductRepository.deleteMyStyleProduct(deleteList);
        for(var a : deleteList) {
            assertThat(styleProductRepository.findById(a).isPresent()).isFalse();
        }


    }
}