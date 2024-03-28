package io.ssafy.mallook.domain.orders.dto.request;

import java.util.List;

public record OrderInsertReq(
        Long totalPrice,
        Long totalFee,
        Long totalCount,
        List<ProductHistoryDto> products
) {
}

