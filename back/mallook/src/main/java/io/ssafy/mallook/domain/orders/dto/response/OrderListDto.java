package io.ssafy.mallook.domain.orders.dto.response;

import io.ssafy.mallook.domain.orders.entity.Orders;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "주문 리스트 조회시 응답 DTO")
@Builder
public record OrderListDto(
        @Schema(description = "주문 id")
        Long id,
        @Schema(description = "구매액")
        Long price,
        @Schema(description = "구매량")
        Long quantity) {

    public static OrderListDto toDto(Orders order) {
        return OrderListDto.builder()
                .id(order.getId())
                .price(order.getTotalPrice() / order.getTotalCount())
                .quantity(order.getTotalCount())
                .build();
    }
}
