package io.ssafy.mallook.domain.product.dto.response;

import io.ssafy.mallook.domain.product.entity.Products;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductsDetailDto(
        String id,
        String mainCategory,
        String subCategory,
        String gender,
        String name,
        Integer price,
        List<String> color,
        List<String> size,
        String brandName,
        Integer fee,
        String image,
        String code,
        String url,
        List<String> tags,
        List<String> detailImages,
        String detailHtml,
        List<String> keywords
) {
    public static ProductsDetailDto toDto(Products products) {
        return ProductsDetailDto.builder()
                .id(products.getId().toString())
                .mainCategory(products.getMainCategory())
                .subCategory(products.getSubCategory())
                .gender(products.getGender())
                .name(products.getName())
                .price(products.getPrice())
                .color(products.getColor())
                .size(products.getSize())
                .brandName(products.getBrandName())
                .fee(products.getFee())
                .image(products.getImage())
                .code(products.getCode())
                .url(products.getUrl())
                .tags(products.getTags())
                .detailImages(products.getDetailImages())
                .detailHtml(products.getDetailHtml())
                .keywords(products.getKeywords())
                .build();
    }
}
