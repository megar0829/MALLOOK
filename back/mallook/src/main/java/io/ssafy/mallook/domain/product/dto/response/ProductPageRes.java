package io.ssafy.mallook.domain.product.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "top100 상품 조회시 응답 DTO")
public record ProductPageRes(
        @Schema(description = "상품 리스트")
        List<ProductsListDto> content,
        @Schema(description = "현재 페이지")
        int currentPage,
        @Schema(description = "전체 페이지")
        int totalPage
) {
}
