package io.ssafy.mallook.domain.script.dto.request;

import java.util.List;

public record ScriptDeleteListDto(
        List<Long> toDeleteList) {
}
