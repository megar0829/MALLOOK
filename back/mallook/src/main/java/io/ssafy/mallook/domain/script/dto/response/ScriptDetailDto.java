package io.ssafy.mallook.domain.script.dto.response;

import io.ssafy.mallook.domain.script.entity.Script;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "스크립트 상세 조회시 응답 DTO")
public record ScriptDetailDto(
        @Schema(description = "스크립트 제목")
        String name,
        @Schema(description = "좋아요 수")
        Integer heartCount,
        @Schema(description = "작성자 닉네임")
        String nickname,
        @Schema(description = "내가 좋아요 했는지 여부")
        boolean hasLiked,
        @Schema(description = "스크립트 대표 이미지")
        String imageUrl,
        @Schema(description = "회원 등급 레벨")
        String memberGrade
        )
{
    public static ScriptDetailDto toDtoNotLogin(Script script, String imageUrl) {
        return ScriptDetailDto.builder()
                .name(script.getName())
                .heartCount(script.getHeartCount())
                .nickname(script.getMember().getNickname())
                .hasLiked(false)
                .imageUrl(imageUrl)
                .memberGrade(script.getMember().getGrade().getLevel().toString())
                .build();
    }

    public static ScriptDetailDto toDto(Script script, boolean hasLiked, String imageUrl) {
        return ScriptDetailDto.builder()
                .name(script.getName())
                .heartCount(script.getHeartCount())
                .nickname(script.getMember().getNickname())
                .hasLiked(hasLiked)
                .imageUrl(imageUrl)
                .build();
    }
}
