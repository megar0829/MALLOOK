package io.ssafy.mallook.domain.product.dto.response;

import io.ssafy.mallook.domain.product.entity.Products;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "몰룩북 이미지 조회시 응답 DTO")
public record ProductImgRes(
        @Schema(description = "상품 id")
        String productId,
        @Schema(description = "상품 이미지 url")
        String imgUrl
) {
    public static ProductImgRes toDto(Products product) {
        return ProductImgRes.builder()
                .productId(product.getId().toString())
                .imgUrl(product.getCrop())
                .build();
    }
}
