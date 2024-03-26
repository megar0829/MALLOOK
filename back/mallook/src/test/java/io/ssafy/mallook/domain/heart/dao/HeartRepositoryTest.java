package io.ssafy.mallook.domain.heart.dao;

import io.ssafy.mallook.domain.heart.entity.Heart;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.product.dao.jpa.ProductRepository;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import io.ssafy.mallook.domain.script.dao.ScriptRepository;
import io.ssafy.mallook.domain.script.entity.Script;
import io.ssafy.mallook.domain.shoppingmall.dao.ShoppingMallRepository;
import io.ssafy.mallook.domain.shoppingmall.entity.ShoppingMall;
import io.ssafy.mallook.domain.style.dao.StyleRepository;
import io.ssafy.mallook.domain.style.entity.Style;
import io.ssafy.mallook.domain.style_product.dao.StyleProductRepository;
import io.ssafy.mallook.domain.style_product.entity.StyleProduct;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles(profiles = "test")
class HeartRepositoryTest {

    @Autowired
    private ShoppingMallRepository shoppingMallRepository;

    private ShoppingMall shoppingMall;

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @Autowired
    private StyleRepository styleRepository;

    private Script script;

    @Autowired
    private ScriptRepository scriptRepository;

    private Style style;

    @Autowired
    private StyleProductRepository styleProductRepository;

    private StyleProduct styleProduct;

    @Autowired
    private HeartRepository heartRepository;

    @Autowired
    private EntityManager entityManager;


    @BeforeEach
    void setUp() {
        shoppingMall = getTestShoppingmall();
        shoppingMallRepository.save(shoppingMall);
        product = getTestProduct();
        productRepository.save(product);
        member = new Member();
        memberRepository.save(member);
        entityManager.flush();
        style = buildStyle(member);
        styleRepository.save(style);
        styleProduct = StyleProduct.builder()
                .style(style)
                .product(product)
                .build();
        styleProductRepository.save(styleProduct);
        script = buildScript(member);
        scriptRepository.save(script);
        entityManager.flush();
        entityManager.clear();
    }

    private Product getTestProduct() {
        return Product.builder()
                .shopingmall(shoppingMall)
                .mainCategory(MainCategory.TOP)
                .subCategory(SubCategory.SPORT)
                .name("테스트 상의")
                .price(1000)
                .quantity(10)
                .size("Large")
                .color("white")
                .fee(500)
                .build();
    }

    private static ShoppingMall getTestShoppingmall() {
        return ShoppingMall.builder()
                .name("테스트 쇼핑몰")
                .url("www.test.com")
                .build();
    }

    private Style buildStyle(Member member) {
        List<StyleProduct> styleProductList = new ArrayList<>();
        styleProductList.add(styleProduct);
        return Style.builder()
                .name("테스트 스타일")
                .heartCount(0L)
                .styleProductList(styleProductList)
                .member(member)
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
        Member proxyMember = memberRepository.getReferenceById(member.getId());
        Style proxyStyle = styleRepository.getReferenceById(style.getId());
        Heart heart = Heart.builder()
                .member(proxyMember)
                .style(proxyStyle)
                .build();
        heartRepository.save(heart);
        Heart getHeart = heartRepository.findByMemberAndStyle(member, style).get();
        assertThat(heart.getId()).isEqualTo(getHeart.getId());
    }
}