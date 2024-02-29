package io.ssafy.mallook.domain.file.dto.response;

import java.util.UUID;

public record FileRes(
        UUID id,
        String originalName,
        String name,
        String url,
        String dir
) {
}
