package io.ssafy.mallook.domain.product.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductHotKeywordDto(
        List<String> hotKeywordList
) {
}
