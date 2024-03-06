package io.ssafy.mallook.domain.heart.dto.request;

import io.ssafy.mallook.domain.heart.entity.Heart;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.entity.Script;
import io.ssafy.mallook.domain.style.entity.Style;
import jakarta.validation.constraints.NotNull;

public record LikeDto(
        @NotNull
        Long targetId
) {

        public Heart toEntity(Member member, Script script) {
                return Heart.builder()
                        .member(member)
                        .script(script)
                        .build();
        }

        public Heart toEntity(Member member, Style style) {
                return Heart.builder()
                        .member(member)
                        .style(style)
                        .build();
        }
}
