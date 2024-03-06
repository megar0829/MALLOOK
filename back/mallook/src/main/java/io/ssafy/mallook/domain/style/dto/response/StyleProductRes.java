package io.ssafy.mallook.domain.style.dto.response;

public record StyleProductRes(
        String name,
        Long price,
        String brandName,
        String image
) {
}
