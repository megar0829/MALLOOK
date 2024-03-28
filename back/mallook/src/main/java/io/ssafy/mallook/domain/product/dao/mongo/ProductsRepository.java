package io.ssafy.mallook.domain.product.dao.mongo;

import io.ssafy.mallook.domain.product.entity.Products;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends MongoRepository<Products, String> {
    Products findFirstByOrderByIdDesc();

//    @Aggregation("{ \"aggregate\" : \"__collection__\", \"pipeline\" : [{ \"$match\" : { \"id\" : \"6604f5dd5fc901aa6386394d\"}}, { \"$project\" : { "reviewList" : { "$slice" : ["$reviews.reviews", 5]}}}]}")
//    @Aggregation(pipeline = {"{$project : { reviewList : { $slice : [$reviews.reviews, 5]}}, {$match : {id: ?0}}"})

    //    Products getProductsDetailWithFirstReview(String id);
    @Aggregation(pipeline = {
            "{$match : {id: ?0}}",
            "{$project : { reviewList : { $slice : ['$reviews.reviews', 5]}}}"
    })
    Products getProductDetailWithLimitedReviews(String id);
}
