package io.ssafy.mallook.domain.style.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "코디북 월드컵 요청 시 응답 DTO")
public record StyledWorldCupDto(

        @Schema(description = "스타일 id")
        Long id,

        @Schema(description = "스타일 제목")
        String name,

        @Schema(description = "좋아요 수")
        Long heartCount,

        @Schema(description = "스타일을 생성한 회원 닉네임")
        String memberNickname,

        @Schema(description = "스타일을 구성하는 상품 이미지 URL")
        String imageUrl,

        @Schema(description = "스타일을 대표하는 키워드 리스트")
        List<String> keywordList
) {
}
