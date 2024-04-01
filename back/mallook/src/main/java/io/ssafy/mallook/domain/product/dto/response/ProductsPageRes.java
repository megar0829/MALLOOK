package io.ssafy.mallook.domain.product.dto.response;

import io.ssafy.mallook.domain.product.entity.Products;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "상품 페이지 조회시 응답 DTO")
public record ProductsPageRes(

        @Schema(description = "상품 리스트")
        List<ProductsListDto> content,

        @Schema(description = "다음 커서")
        String nextCursor
) {
}
