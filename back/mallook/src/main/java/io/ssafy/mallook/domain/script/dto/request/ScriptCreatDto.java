package io.ssafy.mallook.domain.script.dto.request;

import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.script.entity.Script;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ScriptCreatDto(
        @NotBlank(message = "공백일 수 없습니다.")
        @Size(min = 10, message = "너무 짧습니다")
        @Size(max = 200, message = "너무 깁니다")
        String scriptContent) {

    public Script toEntity(Member member) {
        return Script.builder()
                .name(this.scriptContent)
                .member(member)
                .heartCount(0)
                .build();
    }
}
