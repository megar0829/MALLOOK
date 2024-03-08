package io.ssafy.mallook.domain.script.dto.response;

import io.ssafy.mallook.domain.script.entity.Script;
import lombok.Builder;

@Builder
public record ScriptListDto(
        Long id,
        String name,
        Integer heartCount) {

    public static ScriptListDto toDto(Script script) {
        return ScriptListDto.builder()
                .id(script.getId())
                .name(script.getName())
                .heartCount(script.getHeartCount())
                .build();
    }
}
