package io.ssafy.mallook.domain.orders.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductHistoryDto(
        @Schema(description = "상품 id")
        String productId,
        @NotNull
        @Min(1)
        Integer count,
        @NotNull
        @Min(100)
        Integer price,
        @Schema(description = "상품명")
        String name,
        @Schema(description = "사이즈")
        String size,
        @Schema(description = "색상")
        String color
) {
}
