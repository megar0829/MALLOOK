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
        @Schema(description = "배송비")
        Long fee,
        @Schema(description = "구매량")
        Long quantity,
        @Schema(description = "대표 상품 이미지")
        String image,
        @Schema(description = "대표 상품 명")
        String productName,
        @Schema(description = "주문일")
        String createdAt) {

    public static OrderListDto toDto(Orders order) {
        return OrderListDto.builder()
                .id(order.getId())
                .price(order.getTotalPrice())
                .fee(order.getTotalFee())
                .quantity(order.getTotalCount())
                .productName(order.getProductHistoryList().get(0).getProductName())
                .image(order.getProductHistoryList().get(0).getProductImage())
                .createdAt(order.getCreatedAt().toString())
                .build();
    }
}
