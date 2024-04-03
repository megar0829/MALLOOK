package io.ssafy.mallook.domain.product.dao.mongo;

import io.ssafy.mallook.domain.product.entity.Products;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends MongoRepository<Products, String> {
    Products findFirstByOrderById();

    @Aggregation(pipeline = {
            "{$match: {'sub_category': ?0}}",
            "{$sort: {'reviews.count': -1}}",
            "{$limit: 20}"
    })
    List<Products> getRecommendedProducts(String subCategory);
}
