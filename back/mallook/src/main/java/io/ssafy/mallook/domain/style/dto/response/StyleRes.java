package io.ssafy.mallook.domain.style.dto.response;

import io.ssafy.mallook.domain.product.dto.response.ProductsListDto;
import io.ssafy.mallook.domain.style.entity.Style;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "스타일 리스트 정보 요청시 응답 DTO")
public record StyleRes(
        @Schema(description = "스타일 id")
        Long id,
        @Schema(description = "스타일 제목")
        String name,
        @Schema(description = "좋아요 수")
        Long heartCount,
        @Schema(description = "스타일을 생성한 회원 닉네임")
        String memberNickname,
        @Schema(description = "스타일에 포함된 상품 정보")
        List<ProductsListDto> productsListDtoList
) {
}
