package io.ssafy.mallook.domain.chatgpt.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record QuestionDto(
        @NotBlank
        String content) {
}
