package io.ssafy.mallook.domain.product.dto.request;

import java.util.List;

public record ProductHotKeywordDto(
        List<String> hotKeywordList
) {
}
