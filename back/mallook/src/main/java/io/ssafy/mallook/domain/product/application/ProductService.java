package io.ssafy.mallook.domain.product.application;

import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductListDto> getProductList(Pageable pageable, MainCategory mainCategory, SubCategory subCategory);
}
