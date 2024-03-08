package io.ssafy.mallook.domain.cart.application;

import io.ssafy.mallook.domain.cart.dto.request.CartInsertReq;
import io.ssafy.mallook.domain.cart.dto.response.CartDetailRes;
import io.ssafy.mallook.domain.cart.dto.response.CartPageRes;
import org.springframework.data.domain.Pageable;

import java.util.List;

import java.util.UUID;

public interface CartService {
    void insertProductInCart(UUID memberId, CartInsertReq cartInsertReq);
    CartPageRes findProductsInCart(Pageable pageable, UUID memberId);
    void deleteProductInCart(Long productId);
}
