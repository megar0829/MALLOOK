package io.ssafy.mallook.domain.orders.dto.response;

import io.ssafy.mallook.domain.orders.entity.Orders;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "주문 상세 내역 조회시 응답 DTO")
@Builder
public record OrderDetailDto(
        @Schema(description = "총액")
        Long totalPrice,
        @Schema(description = "총 구매 개수")
        Long totalCount,
        @Schema(description = "배송비")
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
