package io.ssafy.mallook.domain.style.dto.response;

import io.ssafy.mallook.domain.style.entity.Style;
import lombok.Builder;

@Builder
public record StyleListRes(
        Long id,
        String name

) {

    public static StyleListRes toDto(Style style) {
        return StyleListRes.builder()
                .id(style.getId())
                .name(style.getName())
                .build();
    }
}
