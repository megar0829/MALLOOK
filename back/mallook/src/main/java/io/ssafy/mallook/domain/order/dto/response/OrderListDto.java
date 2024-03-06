package io.ssafy.mallook.domain.order.dto.response;

import io.ssafy.mallook.domain.order.entity.Order;
import lombok.Builder;

@Builder
public record OrderListDto(
        Long id,
        Long price,
        Long quantity) {

    public static OrderListDto toDto(Order order) {
        return OrderListDto.builder()
                .id(order.getId())
                .price(order.getTotalPrice() / order.getTotalCount())
                .quantity(order.getTotalCount())
                .build();
    }
}
