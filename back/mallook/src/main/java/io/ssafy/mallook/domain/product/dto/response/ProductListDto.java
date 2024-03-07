package io.ssafy.mallook.domain.product.dto.response;

import lombok.Builder;

@Builder
public record ProductListDto(
        String mainCategory,
        String subCategory,
        String name,
        Long price,
        Long quantity,
        String brandName,
        String size,
        Integer fee,
        String image,
        String code,
        String url

) {
}
