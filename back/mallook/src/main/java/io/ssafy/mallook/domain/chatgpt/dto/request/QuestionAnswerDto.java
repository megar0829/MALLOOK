package io.ssafy.mallook.domain.chatgpt.dto.request;

import lombok.Builder;

@Builder
public record QuestionAnswerDto(
        String content) {
}
