package io.ssafy.mallook.domain.cart.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "장바구니에 상품 추가 시 요청 DTO")
public record CartInsertReq(
    @Schema(description="상품 id")
    @NotBlank(message = "상품 id는 공백일 수 없습니다.")
    Long productId,
    @Schema(description="상품 개수")
    @NotBlank(message = "상품 개수는 공백일 수 없습니다.")
    Long count,
    @Schema(description="상품 사이즈")
    @NotBlank(message = "상품 사이즈는 공백일 수 없습니다.")
    String size,

    @Schema(description = "상품 가격")
    @NotBlank(message = "상품 가격은 공백일 수 없습니다.")
    Long price,
    @Schema(description="상품 색상")
    @NotBlank(message = "상품 색상은 공백일 수 없습니다.")
    String color,
    @Schema(description="배송료")
    Integer fee
) {
}
