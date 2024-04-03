package io.ssafy.mallook.domain.heart.dto.request;

import io.ssafy.mallook.domain.heart.script_heart.entity.ScriptHeart;
import io.ssafy.mallook.domain.heart.style_heart.entity.StyleHeart;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.entity.Script;
import io.ssafy.mallook.domain.style.entity.Style;
import jakarta.validation.constraints.NotNull;

public record LikeDto(
        @NotNull
        Long targetId
) {
    public ScriptHeart toEntity(Member member, Script script) {
        return ScriptHeart.builder()
                .member(member)
                .script(script)
                .build();
    }

    public StyleHeart toEntity(Member member, Style style) {
        return StyleHeart.builder()
                .member(member)
                .style(style)
                .build();
    }
}
