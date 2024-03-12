package io.ssafy.mallook.domain.product.dao;

import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import io.ssafy.mallook.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCustomRepository {

    Page<ProductListDto> findAllProduct(Pageable pageable,
                                        String mainCategory,
                                        String subCategory);
}
