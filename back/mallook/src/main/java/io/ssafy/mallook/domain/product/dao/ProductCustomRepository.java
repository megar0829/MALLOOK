package io.ssafy.mallook.domain.product.dao;

import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCustomRepository {

    Page<ProductListDto> findAllProduct(Pageable pageable,
                                        MainCategory mainCategory,
                                        SubCategory subCategory);
}
