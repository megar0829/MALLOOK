package io.ssafy.mallook.domain.cart.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
@Schema(description = "장바구니 내 상품 삭제 요청시 DTO")
public record CartDeleteReq(
        @Schema(description="삭제하고자 하는 상품의 cartProductId")
        Long cartProductId
) {
}