package io.ssafy.mallook.domain.shoppingmall.application;

import io.ssafy.mallook.domain.shoppingmall.dao.ShoppingMallRepository;
import io.ssafy.mallook.domain.shoppingmall.dto.response.ShoppingMallListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShoppingMallServiceImpl implements ShoppingMallService {

    private final ShoppingMallRepository shoppingMallRepository;

    @Override
    public Page<ShoppingMallListDto> getAllMallList(Pageable pageable) {
        return shoppingMallRepository.findAll(pageable)
                .map(ShoppingMallListDto::toDto);
    }
}
