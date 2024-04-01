package io.ssafy.mallook.domain.script.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record ScriptKeywordDto(
        List<String> keywordList
) {
}
