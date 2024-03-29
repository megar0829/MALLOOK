package io.ssafy.mallook.domain.product.dao.mongo;

import io.ssafy.mallook.domain.product.dto.request.ProductHotKeywordDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsDetailDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsListDto;
import io.ssafy.mallook.domain.product.entity.Products;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import java.util.Objects;

import static java.util.Objects.*;

@Repository
@RequiredArgsConstructor
public class ProductsCustomRepositoryImpl implements ProductsCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Slice<ProductsListDto> findByCategory(ObjectId cursor, Pageable pageable, String mainCategory, String subCategory) {
        Query query = new Query().addCriteria(Criteria.where("id").lt(cursor))
                .with(pageable);

        if (!isNull(mainCategory)) {
            query.addCriteria(Criteria.where("mainCategory").is(mainCategory));
        }
        if (!isNull(subCategory)) {
            query.addCriteria(Criteria.where("subCategory").is(subCategory));
        }

        List<ProductsListDto> productsList = mongoTemplate.find(query, Products.class)
                .stream()
                .map(ProductsListDto::toDto)
                .toList();

        boolean hasNext = mongoTemplate.count(query, Products.class) > ((pageable.getPageNumber() + 1) * pageable.getPageSize());
        return new SliceImpl<>(productsList, pageable, hasNext);
    }

    @Override
    public Slice<ProductsListDto> findByProductName(String name, String cursor) {
        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "id"));
        Query query = new Query().addCriteria(Criteria.where("name").regex(name, "i"));

        if (!isNull(cursor) && !cursor.isEmpty()) {
            query.addCriteria(Criteria.where("id").lt(new ObjectId(cursor)));
        }

        List<ProductsListDto> productsList = mongoTemplate.find(query, Products.class)
                .stream()
                .map(ProductsListDto::toDto)
                .toList();
        boolean hasNext = mongoTemplate.count(query, Products.class) > ((pageable.getPageNumber() + 1) * pageable.getPageSize());

        return new SliceImpl<>(productsList, pageable, hasNext);
    }

    @Override
    public Slice<ProductsListDto> findByKeywordList(ProductHotKeywordDto hotKeywordDto, String cursor) {
        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "id"));
        List<String> keywords = hotKeywordDto.hotKeywordList();
        Query query = new Query().addCriteria(Criteria.where("keywords").in(keywords));

        if (!isNull(cursor) && !cursor.isEmpty()) {
            query.addCriteria(Criteria.where("id").lt(new ObjectId(cursor)));
        }

        List<ProductsListDto> productsList = mongoTemplate.find(query, Products.class)
                .stream()
                .map(ProductsListDto::toDto)
                .toList();
        boolean hasNext = mongoTemplate.count(query, Products.class) > ((pageable.getPageNumber() + 1) * pageable.getPageSize());

        return new SliceImpl<>(productsList, pageable, hasNext);
    }

    @Override
    public Products getProductDetailWithLimitedReviews(String id) {
        AggregationOperation matchOperation = Aggregation.match(Criteria.where("_id").is(id));
        AggregationOperation projectOperation = Aggregation.project(
                        "main_category", "sub_category", "gender",
                        "name", "price", "color", "size", "brand_name", "fee",
                        "image", "code", "url", "tags", "detail_images", "detail_html",
                        "keywords")
                .and("reviews.count").as("reviews.count")
                .and("reviews.average_point").as("reviews.average_point")
                .and("reviews.reviews").slice(5).as("reviews.reviews");
        TypedAggregation<Products> aggregation = newAggregation(Products.class, matchOperation, projectOperation);
        AggregationResults<Products> result = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, Products.class);
        return result.getUniqueMappedResult();
    }

    @Override
    public Page<ReviewObject> getReviews(String id, Pageable pageable) {
        AggregationOperation matchOperation = Aggregation.match(Criteria.where("_id").is(id));
        AggregationOperation sliceOperation = Aggregation.project()
                .and("reviews.count").as("reviews.count")
                .and("reviews.average_point").as("reviews.average_point")
                .and("reviews.reviews").slice(pageable.getPageSize(), (int) (pageable.getOffset())).as("reviews.reviews");
        TypedAggregation<Products> aggregation = Aggregation.newAggregation(Products.class, matchOperation, sliceOperation);
        AggregationResults<Products> result = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, Products.class);
        Reviews reviews = Objects.requireNonNull(result.getUniqueMappedResult()).getReviews();
        return new PageImpl<>(reviews.getReviews(), pageable, reviews.getCount());
    }
}
