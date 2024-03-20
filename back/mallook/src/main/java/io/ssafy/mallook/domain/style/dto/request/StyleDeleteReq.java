package io.ssafy.mallook.domain.style.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(description = "내 스타일 삭제시 요청 DTO")
public record StyleDeleteReq(
        @NotEmpty
        @Schema(description = "삭제하고자 하는 스타일의 styleId 리스트")
        List<Long> styleIdList
) {
}
