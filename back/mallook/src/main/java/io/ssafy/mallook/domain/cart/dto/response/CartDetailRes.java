package io.ssafy.mallook.domain.cart.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "장바구니 내 상품 조회시 응답 DTO")
public record CartDetailRes(
        @Schema(description = "장바구니 id")
        Long cartId,
        @Schema(description = "장바구니 내 상품 id")
        Long cartProductId,
        @Schema(description = "상품id")
        Long productId,
        @Schema(description = "상품 가격")
        Long price,
        @Schema(description = "상품개수")
        Long count,
        @Schema(description = "상품명")
        String name,
        @Schema(description = "상품이미지")
        String image,
        @Schema(description = "상품사이즈")
        String size,
        @Schema(description = "상품색상")
        String color,
        @Schema(description = "상품 배송료")
        Integer fee

) {
}
