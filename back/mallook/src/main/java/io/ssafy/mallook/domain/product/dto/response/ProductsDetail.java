package io.ssafy.mallook.domain.product.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductsDetail(
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
        List<String> reviews
) {
}
