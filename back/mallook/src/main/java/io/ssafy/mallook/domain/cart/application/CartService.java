package io.ssafy.mallook.domain.cart.application;

import io.ssafy.mallook.domain.cart.dto.request.CartDeleteReq;
import io.ssafy.mallook.domain.cart.dto.request.CartInsertReq;
import io.ssafy.mallook.domain.cart.dto.response.CartPageRes;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CartService {
    CartPageRes findProductsInCart(Pageable pageable, UUID memberId);
    void insertProductInCart(UUID memberId, CartInsertReq cartInsertReq);
    void deleteProductInCart(UUID memberId, CartDeleteReq cartDeleteReq);
}
