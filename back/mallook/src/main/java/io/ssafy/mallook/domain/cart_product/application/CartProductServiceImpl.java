package io.ssafy.mallook.domain.cart_product.application;

import io.ssafy.mallook.domain.cart.dao.CartRepository;
import io.ssafy.mallook.domain.cart.entity.Cart;
import io.ssafy.mallook.domain.cart_product.dao.CartProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartProductServiceImpl implements CartProductService{
    private final CartProductRepository cartProductRepository;
    private final CartRepository cartRepository;
    @Override
    public void insertProductInCart(UUID memberId, Long productId) {
        var cart = cartRepository.findByMember_Id(memberId).orElse(new Cart());

    }
}
