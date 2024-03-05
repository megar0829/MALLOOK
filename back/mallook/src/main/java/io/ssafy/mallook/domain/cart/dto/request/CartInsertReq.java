package io.ssafy.mallook.domain.cart.dto.request;

public record CartInsertReq(
      Long productId,
      Long productCount,
      String productSize,
      String productColor,
      Long productFee
) {
}
