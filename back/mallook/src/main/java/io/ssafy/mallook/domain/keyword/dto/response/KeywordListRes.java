package io.ssafy.mallook.domain.keyword.dto.response;

import io.ssafy.mallook.domain.style.entity.Style;
import lombok.Builder;

@Builder
public record KeywordListRes(
    String name
) {

    public static KeywordListRes toDto(String name) {
        return KeywordListRes.builder()
                .name(name)
                .build();
    }
}
