package io.ssafy.mallook.domain.product.dto.response;

import io.ssafy.mallook.domain.product.entity.ReviewObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "리뷰 정보 요청시 응답 DTO")
public record ReviewPageRes(
        @Schema(description = "리뷰 리스트")
        List<ReviewObject> content,
        @Schema(description = "현재 페이지")
        int currentPage,
        @Schema(description = "전체 페이지")
        int totalPage
) {
}
