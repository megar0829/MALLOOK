package io.ssafy.mallook.domain.style.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(description = "스타일 등록 시 요청 DTO")
public record StyleInsertReq(
        @Schema(description = "스타일 제목")
        @NotBlank(message = "스타일 제목은 공백일 수 없습니다.")
        String name,
        @Schema(description = "상품 id 리스트")
        @NotEmpty(message = "상품 id리스트는 공백일 수 없습니다.")
        List<String> productIdList,
        String imageUrl
) {
}
