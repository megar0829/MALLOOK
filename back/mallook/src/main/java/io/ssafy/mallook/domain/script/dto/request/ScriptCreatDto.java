package io.ssafy.mallook.domain.script.dto.request;

import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.entity.Script;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
public record ScriptCreatDto(
        @NotEmpty
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
