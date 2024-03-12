package io.ssafy.mallook.domain.style.dto.response;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description="스타일 리스트 정보 요청시 페이지 정보가 포함된 응답 DTO")
public record StylePageRes(
        @Schema(name = "스타일 정보 리스트")
        List<StyleListRes> styleListResList,
        @Schema(name = "전체 페이지")
        int totalPage,
        @Schema(name = "현재 페이지")
        int currentPage
) {
}
