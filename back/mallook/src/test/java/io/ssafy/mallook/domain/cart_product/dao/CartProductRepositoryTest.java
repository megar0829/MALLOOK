package io.ssafy.mallook.domain.cart_product.dao;

import io.ssafy.mallook.domain.cart.dao.CartRepository;
import io.ssafy.mallook.domain.cart.entity.Cart;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = "test")
class CartProductRepositoryTest {

    @Autowired
    private CartProductRepository cartProductRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    ShoppingMallRepository shoppingMallRepository;
    @Autowired
    private EntityManager em;
    private Member member;
    private Product product;
    private ShoppingMall shoppingMall;
    private Cart cart;

    @BeforeEach
    void setUp() {
        member = Mockito.mock(Member.class);
        memberRepository.save(member);
        cart = buildCart(member);
        cartRepository.save(cart);
        shoppingMall = buildShoppingMall();
        shoppingMallRepository.save(shoppingMall);
        product = buildProduct(shoppingMall);
        productRepository.save(product);
    }

    private Cart buildCart(Member member) {
        return Cart.builder()
                .member(member)
                .totalPrice(10000L)
                .totalCount(10L)
                .totalFee(1000)
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

    private ShoppingMall buildShoppingMall() {
        return ShoppingMall.builder()
                .name("testshop")
                .url("www.test.com")
                .build();
    }

    @Test
    @DisplayName("활성화된 카트에 활성화된 상품 중 동일한 상품의 개수 조회 테스트")
    void CountSameProductInCartTest() {

    }

    @Test
    @DisplayName("카트내 상품 삭제")
    void deleteCartProductTest() {
        List<Long> deleteCartList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CartProduct cartProduct = buildCartProduct(cart, product);
            var rs = cartProductRepository.save(cartProduct);
            deleteCartList.add(rs.getId());
        }

        for (Long i : deleteCartList) {
            cartProductRepository.deleteCartProduct(i);
            Optional<CartProduct> cpr = cartProductRepository.findById(i);
            assertThat(cpr.isPresent()).isFalse();
        }
    }
}