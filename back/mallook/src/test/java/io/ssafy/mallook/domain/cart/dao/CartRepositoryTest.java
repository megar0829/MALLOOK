package io.ssafy.mallook.domain.cart.dao;

import io.ssafy.mallook.domain.cart.entity.Cart;
import io.ssafy.mallook.domain.cart_product.dao.CartProductRepository;
import io.ssafy.mallook.domain.cart_product.entity.CartProduct;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.product.dao.jpa.ProductRepository;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import io.ssafy.mallook.domain.shoppingmall.dao.ShoppingMallRepository;
import io.ssafy.mallook.domain.shoppingmall.entity.ShoppingMall;
import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = "test")
@Log4j2
class CartRepositoryTest {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartProductRepository cartProductRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShoppingMallRepository shoppingMallRepository;

    @Autowired
    private EntityManager em;

    private Cart buildCart(Member member) {
        return Cart.builder()
                .member(member)
                .totalPrice(10000L)
                .totalCount(10L)
                .totalFee(1000)
                .build();
    }
    private Product buildProduct(ShoppingMall shoppingMall){
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
    private CartProduct buildCartProduct(Cart cart, Product product) {
        return CartProduct.builder()
                .cart(cart)
                .product(product)
                .productName("test")
                .productCount(1)
                .productPrice(1000)
                .productFee(1000)
                .productSize("s")
                .productColor("red")
                .build();
    }
    private ShoppingMall buildShoppingMall(){
        return ShoppingMall.builder()
                .name("testshop")
                .url("www.test.com")
                .build();
    }

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

    @Test
    @DisplayName("카트 안의 상품 조회")
    void findProductsInCartTest() {
        
    }
    @Test
    @DisplayName("회원의 활성화된 카트 조회")
    void findMyCartByMemberTest() {
        Cart cart = buildCart(member);
        cartRepository.save(cart);
        Optional<Cart> result = cartRepository.findMyCartByMember(member);
        assertThat(result).isNotNull(); //  결과가 null인지 아닌지 확인
    }
}