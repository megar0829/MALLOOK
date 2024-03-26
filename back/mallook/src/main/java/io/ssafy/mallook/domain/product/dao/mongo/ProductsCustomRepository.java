package io.ssafy.mallook.domain.product.dao.mongo;

import io.ssafy.mallook.domain.product.entity.Products;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsCustomRepository {

    Slice<Products> findByCategory(ObjectId cursor, Pageable pageable, String mainCategory, String subCategory);
}
