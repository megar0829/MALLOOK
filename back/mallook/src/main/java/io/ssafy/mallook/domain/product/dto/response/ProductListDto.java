package io.ssafy.mallook.domain.product.dto.response;

import io.ssafy.mallook.domain.product.entity.Product;
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
    public static ProductListDto toDto(Product product) {
        return ProductListDto.builder()
                .name(product.getName())
                .build();
    }
}
