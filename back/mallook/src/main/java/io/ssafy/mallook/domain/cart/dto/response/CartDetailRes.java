package io.ssafy.mallook.domain.cart.dto.response;

public record CartDetailRes(
        Long cartId,
        Long productId,
        Long productPrice,
        Long productCount,
        String productName,
        String productImage,
        String productSize,
        String productColor,
        Long productFee

) {
}
