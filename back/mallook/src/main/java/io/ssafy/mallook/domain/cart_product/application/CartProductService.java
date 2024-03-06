package io.ssafy.mallook.domain.cart_product.application;

import java.util.UUID;

public interface CartProductService {
    void insertProductInCart(UUID memberId, Long productId);
}
