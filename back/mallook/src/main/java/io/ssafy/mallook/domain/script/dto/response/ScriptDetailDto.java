package io.ssafy.mallook.domain.script.dto.response;

import io.ssafy.mallook.domain.script.entity.Script;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "스크립트 상세 조회시 응답 DTO")
public record ScriptDetailDto(
        @Schema(description = "스크립트 제목")
        String name,
        @Schema(description = "좋아요 수")
        Integer heartCount) {

    public static ScriptDetailDto toDto(Script script) {
        return ScriptDetailDto.builder()
                .name(script.getName())
                .heartCount(script.getHeartCount())
                .build();
    }
}
