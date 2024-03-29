package io.ssafy.mallook.domain.script.dto.request;

import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.entity.Script;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "스크립트 생성시 요청 DTO")
public record ScriptCreatDto(
        @NotEmpty
        @Schema(description = "키워드 리스트")
        List<String> keywordsList) {

    public Script toEntity(Member member, String scriptContent) {
        return Script.builder()
                .name(scriptContent)
                .member(member)
                .heartCount(0)
                .totalLike(0)
                .keywordList(keywordsList)
                .build();
    }
}
