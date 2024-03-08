package io.ssafy.mallook.domain.script.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ScriptDeleteListDto(
        @NotNull
        List<Long> toDeleteList) {
}
