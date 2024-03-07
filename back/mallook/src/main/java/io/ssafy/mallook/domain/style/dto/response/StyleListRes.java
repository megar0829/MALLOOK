package io.ssafy.mallook.domain.style.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "스타일 리스트 정보 요청시 응답 DTO")
public record StyleListRes(
        @Schema(name = "스타일 id")
        Long id,
        @Schema(name="스타일 제목")
        String name

) {
}
