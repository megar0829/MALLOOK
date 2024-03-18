package io.ssafy.mallook.domain.shoppingmall.dto.response;

import io.ssafy.mallook.domain.shoppingmall.entity.ShoppingMall;
import lombok.Builder;

@Builder
public record ShoppingMallListDto(
        Long id,
        String name,
        String url
) {
    public static ShoppingMallListDto toDto(ShoppingMall shoppingMall) {
        return ShoppingMallListDto.builder()
                .id(shoppingMall.getId())
                .name(shoppingMall.getName())
                .url(shoppingMall.getUrl())
                .build();
    }
}
