package io.ssafy.mallook.global.batch.dto;

import io.ssafy.mallook.domain.script.entity.Script;

import java.util.List;

public record TopScriptDto(
        List<Script> scripts
) {
}
