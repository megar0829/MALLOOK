package io.ssafy.mallook.domain.shoppingmall.application;

import io.ssafy.mallook.domain.shoppingmall.dto.response.ShoppingMallListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ShoppingMallService {
    Slice<ShoppingMallListDto> getAllMallList(Pageable pageable);
}
