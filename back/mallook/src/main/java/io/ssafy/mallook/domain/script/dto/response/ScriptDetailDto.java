package io.ssafy.mallook.domain.script.dto.response;

import io.ssafy.mallook.domain.script.entity.Script;
import lombok.Builder;

@Builder
public record ScriptDetailDto(
        String name,
        Integer heartCount) {

    public static ScriptDetailDto toDto(Script script) {
        return ScriptDetailDto.builder()
                .name(script.getName())
                .heartCount(script.getHeartCount())
                .build();
    }
}
