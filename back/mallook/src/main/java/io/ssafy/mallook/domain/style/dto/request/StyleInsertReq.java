package io.ssafy.mallook.domain.style.dto.request;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Schema(description = "스타일 등록 시 요청 DTO")
public record StyleInsertReq(
        @Schema(name="스타일 제목")
        @NotBlank(message = "스타일 제목은 공백일 수 없습니다.")
        String name,
        @Schema(name="상품 id 리스트")
        @NotBlank(message = "상품 id리스트는 공백일 수 없습니다.")
        List<Long> productIdList
) {
}
