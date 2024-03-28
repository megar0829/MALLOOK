package io.ssafy.mallook.domain.product.dao.mongo;

import io.ssafy.mallook.domain.product.dto.request.ProductHotKeywordDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsDetailDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsListDto;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsCustomRepository {

    Slice<ProductsListDto> findByCategory(ObjectId cursor, Pageable pageable, String mainCategory, String subCategory);

    Slice<ProductsListDto> findByProductName(String name, String cursor);

    Slice<ProductsListDto> findByKeywordList(ProductHotKeywordDto hotKeywordDto, String cursor);
}
