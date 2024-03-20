package io.ssafy.mallook.domain.orders.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderDeleteDto(
        @NotEmpty
        List<Long> deleteList
) {
}
