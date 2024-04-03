package io.ssafy.mallook.domain.style.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "스타일에 포함된 상품 정보 요청시 응답 DTO")
public record StyleProductRes(
        @Schema(description = "상품 id")
        String productsId,
        @Schema(description="상품명")
        String name,
        @Schema(description = "상품 가격")
        Integer price,
        @Schema(description = "브랜드명")
        String brandName,
        @Schema(description = "이미지")
        String image
) {
}
