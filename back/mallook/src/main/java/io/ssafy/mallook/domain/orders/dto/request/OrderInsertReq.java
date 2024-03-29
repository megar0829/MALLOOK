package io.ssafy.mallook.domain.orders.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "장바구니에서 상품 구매 시 요청 DTO")
public record OrderInsertReq(
        @Schema(description = "총액")
        Long totalPrice,
        @Schema(description = "배송비")
        Long totalFee,
        @Schema(description = "총 개수")
        Long totalCount,
        @Schema(description = "장바구니 id")
        Long cartId,
        @Schema(description = "장바구니 내 상품 번호 리스트")
        List<Long> cartProductList
) {
}

