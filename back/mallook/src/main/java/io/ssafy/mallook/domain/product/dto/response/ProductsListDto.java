package io.ssafy.mallook.domain.product.dto.response;
import java.util.List;
public record ProductsListDto (
        String id,
        String mainCategory,
        String subCategory,
        String gender,
        String name,
        Integer price,
        String brandName,
        List<String> size,
        Integer fee,
        List<String> tags,
        List<String> detailImages,
        String detailHtml,
        String code,
        String url
){
}
