package io.ssafy.mallook.domain.cart.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="장바구니 내 상품 조회시 응답 DTO")
public record CartDetailRes(
        @Schema(name="장바구니 id")
        Long cartId,
        @Schema(name="장바구니 내 상품 id")
        Long cartProductId,
        @Schema(name="상품id")
        Long productId,
        @Schema(name="상품 가격")
        Long productPrice,
        @Schema(name="상품개수")
        Long productCount,
        @Schema(name="상품명")
        String productName,
        @Schema(name="상품이미지")
        String productImage,
        @Schema(name="상품사이즈")
        String productSize,
        @Schema(name="상품색상")
        String productColor,
        @Schema(name="상품 배송료")
        Long productFee

) {
}
