package io.ssafy.mallook.domain.chatgpt.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
public record GptResponseDto(
        @NotBlank
        String answer
) {
}
