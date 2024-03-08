package io.ssafy.mallook.domain.style.dto.response;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description = "스타일 정보 조회시 응답 DTO")
public record StyleDetailRes(
        @Schema(name = "스타일을 생성한 회원 닉네임")
        String memberNickname,
        @Schema(name = "스타일 제목")
        String name,
        @Schema(name = "좋아요 수")
        Long heartCount,
        @Schema(name = "스타일에 포함된 상품 리스트")
        List<StyleProductRes> styleProductResList

) {
}
