package io.ssafy.mallook.domain.script.dto.response;

import io.ssafy.mallook.domain.script.entity.Script;
import lombok.Builder;

@Builder
public record ScriptListDto(
        String name,
        Integer heartCount) {

    public static ScriptListDto toDto(Script script) {
        return ScriptListDto.builder()
                .name(script.getName())
                .heartCount(script.getHeartCount())
                .build();
    }
}
