package io.ssafy.mallook.domain.style_product.dao;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.product.dao.jpa.ProductRepository;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import io.ssafy.mallook.domain.shoppingmall.dao.ShoppingMallRepository;
import io.ssafy.mallook.domain.shoppingmall.entity.ShoppingMall;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.entity.Style;
import io.ssafy.mallook.domain.style_product.entity.StyleProduct;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
class StyleProductRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    StyleRepository styleRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    StyleProductRepository styleProductRepository;
    @Autowired
    ShoppingMallRepository shoppingMallRepository;
    @Autowired
    EntityManager em;
    private Member member;
    private Product product;
    private ShoppingMall shoppingMall;

    @BeforeEach

    void setUp() {
        member = new Member();
        memberRepository.save(member);
        shoppingMall = buildShoppingMall();
        shoppingMallRepository.save(shoppingMall);
        em.flush();
        em.clear();
    }

    private Style buildStyle(Member member) {
        return Style.builder()
                .name("테스트용 제목")
                .member(member)
                .heartCount(0L)
                .totalLike(0)
                .build();
    }

    private StyleProduct buildStyleProduct(Style style, Product product) {
        return StyleProduct.builder()
                .style(style)
                .product(product)
                .build();
    }

    private Product buildProduct(ShoppingMall shoppingMall) {
        return Product.builder()
                .mainCategory(MainCategory.TOP)
                .subCategory(SubCategory.FORMAL)
                .name("테스트옷")
                .price(1000)
                .quantity(10)
                .size("s")
                .color("red")
                .fee(1000)
                .shopingmall(shoppingMall)
                .build();
    }

    private ShoppingMall buildShoppingMall() {
        return ShoppingMall.builder()
                .name("testshop")
                .url("www.test.com")
                .build();
    }

    @Test
    @DisplayName("내가 저장한 스타일 내 상품 제거")
    void deleteMyStyleProductTest() {
        Style style = buildStyle(member);
        styleRepository.save(style);
        List<Long> deleteList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Product pd = buildProduct(shoppingMall);
            productRepository.save(pd);
            var result = styleProductRepository.save(buildStyleProduct(style, pd));
            System.out.println("#####################" + result.getId());
            deleteList.add(result.getId());
        }
        styleProductRepository.deleteMyStyleProduct(deleteList);
        for (var pk : deleteList) {
            assertThat(styleProductRepository.findById(pk).isPresent()).isFalse();
        }
    }
}