package io.ssafy.mallook.global.batch.dto;

import io.ssafy.mallook.domain.style.entity.Style;
import lombok.Builder;

import java.util.List;

@Builder
public record TopStyleDto(
        List<Style> styles
) {
}
