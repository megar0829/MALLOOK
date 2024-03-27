package io.ssafy.mallook.domain.product.dto.response;
import io.ssafy.mallook.domain.product.entity.Products;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductsListDto (
        String id,
        String mainCategory,
        String subCategory,
        String gender,
        String name,
        Integer price,
        String brandName,
        List<String> size,
        Integer fee,
        List<String> tags,
        List<String> detailImages,
        String detailHtml,
        String code,
        String url,
        String image
){

    public static ProductsListDto toDto(Products products) {
        return ProductsListDto.builder()
                .id(products.getId().toString())
                .mainCategory(products.getMainCategory())
                .subCategory(products.getSubCategory())
                .gender(products.getGender())
                .name(products.getName())
                .price(products.getPrice())
                .brandName(products.getBrandName())
                .fee(products.getFee())
                .image(products.getImage())
                .code(products.getCode())
                .url(products.getUrl())
                .tags(products.getTags())
                .detailImages(products.getDetailImages())
                .detailHtml(products.getDetailHtml())
                .build();
    }
}
