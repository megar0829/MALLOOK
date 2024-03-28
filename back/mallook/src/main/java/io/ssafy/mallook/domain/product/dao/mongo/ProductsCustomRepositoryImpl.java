package io.ssafy.mallook.domain.product.dao.mongo;

import io.ssafy.mallook.domain.product.dto.response.ProductsListDto;
import io.ssafy.mallook.domain.product.entity.Products;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mongodb.client.model.Projections.slice;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Repository
public class ProductsCustomRepositoryImpl implements ProductsCustomRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Slice<ProductsListDto> getProductsListByCategory(ObjectId cursor, Pageable pageable, String mainCategory, String subCategory) {
        Query query = new Query().addCriteria(Criteria.where("id").lt(cursor))
                .with(pageable);

        if (mainCategory != null) {
            query.addCriteria(Criteria.where("mainCategory").is(mainCategory));
        }
        if (subCategory != null) {
            query.addCriteria(Criteria.where("subCategory").is(subCategory));
        }

        List<ProductsListDto> productsList = mongoTemplate.find(query, Products.class)
                .stream().map(ele -> new ProductsListDto(
                        ele.getId().toString(),
                        ele.getMainCategory(),
                        ele.getSubCategory(),
                        ele.getGender(),
                        ele.getName(),
                        ele.getPrice(),
                        ele.getBrandName(),
                        ele.getSize(),
                        ele.getFee(),
                        ele.getTags(),
                        ele.getDetailImages(),
                        ele.getDetailHtml(),
                        ele.getCode(),
                        ele.getUrl())).toList();

        boolean hasNext = mongoTemplate.count(query, Products.class) > ((pageable.getPageNumber() + 1) * pageable.getPageSize());
        return new SliceImpl<>(productsList, pageable, hasNext);
    }

    @Override
    public Products getProductDetailWithFirstReviews(String id) {
        AggregationOperation projectOperation = Aggregation.project().and(ArrayOperators.Slice.sliceArrayOf("$reviews.reviews")
                .itemCount(5)).as("reviewList");
        TypedAggregation<Products> aggregation = newAggregation(Products.class, projectOperation);
        return mongoTemplate.aggregate(aggregation, Products.class).getUniqueMappedResult();
    }

}
