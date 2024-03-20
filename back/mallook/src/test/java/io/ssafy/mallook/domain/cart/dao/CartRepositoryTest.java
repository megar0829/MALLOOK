package io.ssafy.mallook.domain.cart.dao;

import io.ssafy.mallook.domain.cart.entity.Cart;
import io.ssafy.mallook.domain.cart_product.entity.CartProduct;
import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.script.entity.Script;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(profiles = {"dev", "local"})
class CartRepositoryTest {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    MemberRepository memberRepository;

    private Cart buildCart(Member member, List<CartProduct> cartProductList) {
        return Cart.builder()
                .member(member)
                .cartProductList(cartProductList)
                .totalPrice(10000L)
                .totalCount(10L)
                .totalFee(1000)
                .build();
    }
    private CartProduct buildCartProduct(Product product, Long count, Long price, String name, String size, String color) {
        return CartProduct.builder()
                .product(product)
                .productName(name)
                .productCount(count)
                .productPrice(price)
                .productSize(size)
                .productColor(color)
                .build();
    }
    @BeforeEach
    void setUp() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
    }

    @Test
    @DisplayName("카트 안의 상품 조회")
    void findProductsInCartTest() {
        
    }
    @Test
    @DisplayName("회원의 활성화된 카트 조회")
    void findMyCartByMemberTest() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);

        List<CartProduct> cartProductList = new ArrayList<>();
        for (int i = 0; i < 3; i ++) {
            CartProduct cartProduct = Mockito.mock(CartProduct.class);
            cartProductList.add(cartProduct);
        }
        Cart cart = buildCart(member, cartProductList);
        cartRepository.save(cart);

        Optional<Cart> result = cartRepository.findMyCartByMember(member);
        assertThat(result).isNotNull(); //  결과가 null인지 아닌지 확인
    }

}