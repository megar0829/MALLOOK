package io.ssafy.mallook.domain.script.dto.response;

import io.ssafy.mallook.domain.product.dto.response.ProductsListDto;
import lombok.Builder;

import java.util.List;

@Builder
public record ScriptProductDto(
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
        String code,
        String url,
        String image)
{
    public static ScriptProductDto toScriptProductDto(ProductsListDto productsListDto) {
        return ScriptProductDto.builder()
                .id(productsListDto.id())
                .mainCategory(productsListDto.mainCategory())
                .subCategory(productsListDto.subCategory())
                .gender(productsListDto.gender())
                .name(productsListDto.name())
                .price(productsListDto.price())
                .brandName(productsListDto.brandName())
                .fee(productsListDto.fee())
                .image(productsListDto.image())
                .code(productsListDto.code())
                .url(productsListDto.url())
                .tags(productsListDto.tags())
                .detailImages(productsListDto.detailImages())
                .build();
    }
}
