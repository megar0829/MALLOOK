package io.ssafy.mallook.domain.style_product.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(description = "내 스타일에 포함되는 상품 삭제 요청시 요청 DTO")
public record StyleProductDeleteReq(
        @NotEmpty
        @Schema(description = "삭제하고자 하는 스타일 내 상품의 styleProductId 리스트")
        List<Long> styleProductIdList
) {
}
