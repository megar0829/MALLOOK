package io.ssafy.mallook.domain.orders.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
@Schema(description = "주문 삭제 시 요청 DTO")
public record OrderDeleteDto(
        @NotEmpty
        @Schema(description = "")
        List<Long> deleteList
) {
}
