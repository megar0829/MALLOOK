package io.ssafy.mallook.global.batch.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TopStyleDto(
        List<Long> styleIdList
) {
}
