package io.ssafy.mallook.domain.order.dto.response;

import io.ssafy.mallook.domain.order.entity.Order;
import lombok.Builder;

@Builder
public record OrderDetailDto(
        Long totalPrice,
        Long totalCount,
        Long totalFee
) {

    public static OrderDetailDto toDto(Order order) {
        return OrderDetailDto.builder()
                .totalPrice(order.getTotalPrice())
                .totalCount(order.getTotalCount())
                .totalFee(order.getTotalFee())
                .build();
    }
}
