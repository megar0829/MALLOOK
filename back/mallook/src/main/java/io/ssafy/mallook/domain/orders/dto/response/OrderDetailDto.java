package io.ssafy.mallook.domain.orders.dto.response;

import io.ssafy.mallook.domain.orders.entity.Orders;
import lombok.Builder;

@Builder
public record OrderDetailDto(
        Long totalPrice,
        Long totalCount,
        Long totalFee
) {

    public static OrderDetailDto toDto(Orders order) {
        return OrderDetailDto.builder()
                .totalPrice(order.getTotalPrice())
                .totalCount(order.getTotalCount())
                .totalFee(order.getTotalFee())
                .build();
    }
}
