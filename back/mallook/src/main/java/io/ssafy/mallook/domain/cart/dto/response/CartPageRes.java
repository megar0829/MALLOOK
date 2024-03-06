package io.ssafy.mallook.domain.cart.dto.response;

import java.util.List;
public record CartPageRes(
        List<CartDetailRes> cartDetailList,
        int totalPage,
        int currentPage
) {
}
