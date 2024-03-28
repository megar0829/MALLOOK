package io.ssafy.mallook.domain.product.dao.jpa;

import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCustomRepository {

    Slice<ProductListDto> findAllProduct(Long cursor, Pageable pageable, MainCategory mainCategory, SubCategory subCategory);
}
