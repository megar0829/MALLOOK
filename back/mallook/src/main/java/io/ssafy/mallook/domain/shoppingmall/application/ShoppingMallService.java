package io.ssafy.mallook.domain.shoppingmall.application;

import io.ssafy.mallook.domain.shoppingmall.dto.response.ShoppingMallListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShoppingMallService {
    Page<ShoppingMallListDto> getAllMallList(Pageable pageable);
}
