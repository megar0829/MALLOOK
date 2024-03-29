package io.ssafy.mallook.domain.orders.dto.request;

import java.util.List;

public record OrderDirectInsertReq(
        Long totalPrice,
        Long totalFee,
        Long totalCount,
        ProductHistoryDto products
) {
}
