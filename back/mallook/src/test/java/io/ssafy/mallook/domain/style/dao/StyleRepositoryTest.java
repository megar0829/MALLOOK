package io.ssafy.mallook.domain.style.dao;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.product.dao.ProductRepository;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import io.ssafy.mallook.domain.shoppingmall.dao.ShoppingMallRepository;
import io.ssafy.mallook.domain.shoppingmall.entity.ShoppingMall;
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
@ActiveProfiles(profiles = {"dev", "local"})
class StyleRepositoryTest {

    @Autowired
    StyleRepository styleRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    StyleProductRepository styleProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShoppingMallRepository shoppingMallRepository;
    private Member member;
    private Product product;
    private ShoppingMall shoppingMall;
    @BeforeEach
    void setUp() {
        member = Mockito.mock(Member.class);
        memberRepository.save(member);
        shoppingMall = buildShoppingMall();
        shoppingMallRepository.save(shoppingMall);
        product = buildProduct(shoppingMall);
        productRepository.save(product);

    }

    private Style buildStyle(Member member) {
        return Style.builder()
                .name("테스트용 제목")
                .member(member)
                .heartCount(0L)
                .build();
    }
    private StyleProduct buildStyleProduct(Style style, Product product){
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
    @DisplayName("페이지를 적용하여 스타일 전체 조회")
    void findAllTest() {
        for (int i = 0; i < 20; i ++) {
            // 스타일
            Style style = buildStyle(member);
            styleRepository.save(style);
            // style product 저장
            for (int j = 0 ; j < 2; j ++) {
                StyleProduct sp = buildStyleProduct(style, product);
                styleProductRepository.save(sp);
            }
        }
        Pageable pageable = PageRequest.of(0, 20);
        var result = styleRepository.findAll(pageable);
        assertThat(Objects.nonNull(result)).isTrue();
    }
    @Test
    @DisplayName("내가 등록한 스타일 삭제")
    void deleteMyStyleTest() {
        List<Long> deleteList = new ArrayList<>();
        // 스타일 작성 멤버
        for (int i = 0; i < 20; i ++) {
            // 스타일
            Style style = buildStyle(member);
            var rs = styleRepository.save(style);
            deleteList.add(rs.getId());
            // 스타일에 속하는 상품 리스트
            for (int j = 0 ; j < 2; j ++) {
                StyleProduct sp = buildStyleProduct(style, product);
                styleProductRepository.save(sp);
            }
        }
        // style 삭제 확인
        styleRepository.deleteMyStyle(member.getId(), deleteList);
        for (var a : deleteList) {
            assertThat(styleRepository.findById(a).isPresent()).isFalse();
            // style product 확인
            assertThat(styleProductRepository.findByStyle_Id(a).isPresent()).isFalse();
        }
    }
}