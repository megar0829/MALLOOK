package io.ssafy.mallook.domain.order.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderDeleteDto(
        @NotNull
        List<Long> deleteList
) {
}
