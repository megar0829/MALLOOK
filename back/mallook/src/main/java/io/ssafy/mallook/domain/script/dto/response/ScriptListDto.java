package io.ssafy.mallook.domain.script.dto.response;

import io.ssafy.mallook.domain.script.entity.Script;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "스크립트 리스트 조회시 요청 DTO")
public record ScriptListDto(
        @Schema(description = "스크립트 id")
        Long id,
        @Schema(description = "스크립트 제목")
        String name,
        @Schema(description = "좋아요 수")
        Integer heartCount) {

    public static ScriptListDto toDto(Script script) {
        return ScriptListDto.builder()
                .id(script.getId())
                .name(script.getName())
                .heartCount(script.getHeartCount())
                .build();
    }
}
