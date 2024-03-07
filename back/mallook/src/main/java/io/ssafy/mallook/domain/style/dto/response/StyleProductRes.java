package io.ssafy.mallook.domain.style.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "스타일에 포함된 상품 정보 요청시 응답 DTO")
public record StyleProductRes(
        @Schema(name="상품명")
        String name,
        @Schema(name="상품 가격")
        Long price,
        @Schema(name="브랜드명")
        String brandName,
        @Schema(name="이미지")
        String image
) {
}
