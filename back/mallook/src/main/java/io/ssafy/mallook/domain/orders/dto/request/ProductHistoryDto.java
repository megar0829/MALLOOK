package io.ssafy.mallook.domain.orders.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductHistoryDto(
        String productId,
        @NotNull
        @Min(1)
        Integer count,
        @NotNull
        @Min(100)
        Integer price,
        String name,
        String size,
        String color
) {
}
