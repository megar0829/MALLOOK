package io.ssafy.mallook.domain.product.dao.mongo;

import io.ssafy.mallook.domain.product.entity.Products;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends MongoRepository<Products, String>{

    Products findFirstByOrderByIdAsc();
    Slice<Products> findProductsByMainCategoryAndSubCategoryAndIdGreaterThan(
            String mainCategory, String subCategory, ObjectId cursor, Pageable pageable);
    Slice<Products> findProductsByMainCategoryAndSubCategory(
            String mainCategory, String subCategory, Pageable pageable);

}
