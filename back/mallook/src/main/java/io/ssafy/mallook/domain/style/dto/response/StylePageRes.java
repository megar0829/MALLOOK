package io.ssafy.mallook.domain.style.dto.response;
import java.util.List;
public record StylePageRes(
        List<StyleListRes> styleListResList,
        int totalPage,
        int currentPage
) {
}
