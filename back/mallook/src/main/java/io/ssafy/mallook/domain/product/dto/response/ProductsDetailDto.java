package io.ssafy.mallook.domain.product.dto.response;

import io.ssafy.mallook.domain.product.entity.Products;
import io.ssafy.mallook.domain.product.entity.Reviews;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "상품 상세 조회시 응답 DTO")
public record ProductsDetailDto(
        @Schema(description = "상품 id")
        String id,
        @Schema(description = "메인 카테고리")
        String mainCategory,
        @Schema(description = "서브 카테고리")
        String subCategory,
        @Schema(description = "성별")
        String gender,
        @Schema(description = "상품명")
        String name,
        @Schema(description = "가격")
        Integer price,
        @Schema(description = "색상 리스트")
        List<String> color,
        @Schema(description = "사이즈 리스트")
        List<String> size,
        @Schema(description = "브랜드명")
        String brandName,
        @Schema(description = "배송비")
        Integer fee,
        @Schema(description = "이미지")
        String image,
        @Schema(description = "상품코드")
        String code,
        @Schema(description = "상품 url")
        String url,
        @Schema(description = "태그")
        List<String> tags,
        @Schema(description = "디테일 이미지")
        List<String> detailImages,
        @Schema(description = "상세이미지 html")
        String detailHtml,
        @Schema(description = "키워드 리스트")
        List<String> keywords,
        @Schema(description = "리뷰 리스트 중 처음 5개")
        Reviews review
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
                .review(products.getReview())
                .build();
    }
}
