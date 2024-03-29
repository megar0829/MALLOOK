package io.ssafy.mallook.global.batch.dto;

import io.ssafy.mallook.domain.style.entity.Style;

import java.util.List;

public record TopStyleDto(
        List<Style> styles
) {
}
