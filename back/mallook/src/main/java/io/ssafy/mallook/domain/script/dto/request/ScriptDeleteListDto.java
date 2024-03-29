package io.ssafy.mallook.domain.script.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(description = "스크립트 삭제 시 요청 DTO")
public record ScriptDeleteListDto(
        @NotEmpty
        @Schema(description = "삭제할 스크립트 id 리스트")
        List<Long> toDeleteList) {
}
