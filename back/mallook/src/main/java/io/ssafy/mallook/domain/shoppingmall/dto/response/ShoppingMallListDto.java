package io.ssafy.mallook.domain.shoppingmall.dto.response;

import io.ssafy.mallook.domain.shoppingmall.entity.ShoppingMall;
import lombok.Builder;

@Builder
public record ShoppingMallListDto(
        String name,
        String url
) {
    public static ShoppingMallListDto toDto(ShoppingMall shoppingMall) {
        return ShoppingMallListDto.builder()
                .name(shoppingMall.getName())
                .url(shoppingMall.getUrl())
                .build();
    }
}
