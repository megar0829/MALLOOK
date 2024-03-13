package io.ssafy.mallook.domain.cart_product.dao;

import io.ssafy.mallook.domain.cart.dao.CartRepository;
import io.ssafy.mallook.domain.cart.entity.Cart;
import io.ssafy.mallook.domain.cart_product.entity.CartProduct;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CartProductRepositoryTest {

    @Autowired
    private CartProductRepository cartProductRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EntityManager entityManager;
    @BeforeEach
    void setUp() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
    }
    private CartProduct buildCartProduct(Cart cart, Long price, Long count, Integer fee){
        return CartProduct.builder()
                .cart(cart)
                .productColor("테스트용색")
                .productSize("테스트용사이즈")
                .productImage("테스트용이미지")
                .productPrice(price)
                .productCount(count)
                .productFee(fee)
                .build();
    }
    @Test
    @DisplayName("활성화된 카트에 활성화된 상품 중 동일한 상품의 개수 조회 테스트")
    void CountSameProductInCartTest() {

    }

    @Test
    @DisplayName("카트내 상품 삭제")
    void deleteCartProductTest() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
        Cart cart = Mockito.mock(Cart.class);
        cartRepository.save(cart);
        List<Long> deleteCartList = new ArrayList<>();
        for (int i = 0; i < 3; i ++) {
            CartProduct cartProduct = buildCartProduct(cart, 1000L, 1L, 100);
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