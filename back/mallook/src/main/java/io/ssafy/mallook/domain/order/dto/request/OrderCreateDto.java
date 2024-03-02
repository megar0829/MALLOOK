package io.ssafy.mallook.domain.order.dto.request;

import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.order.entity.Order;
import jakarta.validation.constraints.NotNull;

public record OrderCreateDto(
        @NotNull
        Long price,

        @NotNull
        Long quantity,

        @NotNull
        Long fee) {

    public Order toEntity(Member member) {
        return Order.builder()
                .memberId(member)
                .totalPrice(this.price * this.quantity)
                .totalCount(quantity)
                .totalFee(fee)
                .build();
    }
}
