package io.ssafy.mallook.domain.product.application;

import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductListDto> getProductList(Pageable pageable);
}
