package io.ssafy.mallook.domain.cart.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="장바구니 내 상품 조회시 응답 DTO")
public record CartDetailRes(
        @Schema(description="장바구니 id")
        Long cartId,
        @Schema(description="장바구니 내 상품 id")
        Long cartProductId,
        @Schema(description="상품id")
        Long productId,
        @Schema(description="상품 가격")
        Long productPrice,
        @Schema(description="상품개수")
        Long productCount,
        @Schema(description="상품명")
        String productName,
        @Schema(description="상품이미지")
        String productImage,
        @Schema(description="상품사이즈")
        String productSize,
        @Schema(description="상품색상")
        String productColor,
        @Schema(description="상품 배송료")
        Integer productFee

) {
}
