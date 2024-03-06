package io.ssafy.mallook.domain.style.dto.response;
import java.util.List;
public record StyleDetailRes(
        String memberNickname,
        String name,
        Long heartCount,
        List<StyleProductRes> styleProductResList

) {
}
