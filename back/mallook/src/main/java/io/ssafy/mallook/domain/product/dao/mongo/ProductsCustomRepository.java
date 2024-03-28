package io.ssafy.mallook.domain.product.dao.mongo;

import io.ssafy.mallook.domain.product.dto.response.ProductsListDto;
import io.ssafy.mallook.domain.product.entity.Products;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsCustomRepository {

    Slice<ProductsListDto> getProductsListByCategory(ObjectId cursor, Pageable pageable, String mainCategory, String subCategory);
    Products getProductDetailWithFirstReviews(String id);
}
