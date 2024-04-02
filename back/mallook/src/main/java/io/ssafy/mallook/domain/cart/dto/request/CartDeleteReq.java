package io.ssafy.mallook.domain.cart.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "장바구니 전체 삭제 DTO")
public record CartDeleteReq(
        @Schema(description = "장바구니 id")
        Long cartId
) {
}
