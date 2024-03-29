package io.ssafy.mallook.domain.orders.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "직접 구매 요청 시 요청 DTO")
public record OrderDirectInsertReq(
        @Schema(description = "총액")
        Long totalPrice,
        @Schema(description = "배송비")
        Long totalFee,
        @Schema(description = "개수")
        Long totalCount,
        @Schema(description = "상품 정보")
        ProductHistoryDto products
) {
}
