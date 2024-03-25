package io.ssafy.mallook.domain.style.dto.response;

import io.ssafy.mallook.domain.style.entity.Style;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "스타일 리스트 정보 요청시 응답 DTO")
public record StyleRes(
        @Schema(description = "스타일 id")
        Long id,
        @Schema(description = "스타일 제목")
        String name,
        @Schema(description = "좋아요 수")
        Long heartCount,
        @Schema(description = "스타일을 생성한 회원 닉네임")
        String memberNickname
) {
    public static StyleRes toDto(Style style) {
        return StyleRes.builder()
                .id(style.getId())
                .name(style.getName())
                .heartCount(style.getHeartCount())
                .memberNickname(style.getMember().getNickname())
                .build();
    }
}
