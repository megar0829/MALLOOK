package io.ssafy.mallook.domain.script.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ScriptDeleteListDto(
        @NotEmpty
        List<Long> toDeleteList) {
}
