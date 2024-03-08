package io.ssafy.mallook.domain.style.dto.request;
import java.util.List;

public record StyleInsertReq(
        String name,
        List<Long> productIdList
) {
}
