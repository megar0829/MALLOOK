package io.ssafy.mallook.global.batch.dto;

import io.ssafy.mallook.domain.script.entity.Script;
import lombok.Builder;

import java.util.List;

@Builder
public record TopScriptDto(
        List<Script> scripts
) {
}
