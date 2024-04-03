package io.ssafy.mallook.domain.script.dto.response;

import io.ssafy.mallook.domain.script.entity.Script;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "스크립트 리스트 조회시 요청 DTO")
public record ScriptListDto(
        @Schema(description = "스크립트 id")
        Long id,
        @Schema(description = "스크립트 제목")
        String name,
        @Schema(description = "좋아요 수")
        Integer heartCount,
        @Schema(description = "작성자 닉네임")
        String nickname,
        @Schema(description = "스크립트 대표 이미지")
        String imageUrl,

        @Schema(description = "멤버 등급")
        String memberGrade
) {
    public static ScriptListDto toDto(Script script, String imageUrl) {
        return ScriptListDto.builder()
                .id(script.getId())
                .name(script.getName())
                .nickname(script.getMember().getNickname())
                .heartCount(script.getHeartCount())
                .imageUrl(imageUrl)
                .memberGrade(script.getMember().getGrade().getLevel().toString())
                .build();
    }
}
