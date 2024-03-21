package io.ssafy.mallook.domain.chatgpt.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
public record GptResponseDto(
        String answer
) {
}
