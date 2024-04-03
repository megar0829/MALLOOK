package io.ssafy.mallook.domain.product.dao.mongo;

import io.ssafy.mallook.domain.product.dto.response.ProductImgRes;
import io.ssafy.mallook.domain.product.dto.response.ProductsDetailDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsListDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsPageRes;
import io.ssafy.mallook.domain.product.entity.Products;
import io.ssafy.mallook.domain.product.entity.ReviewObject;
import io.ssafy.mallook.domain.product.entity.Reviews;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Repository
@RequiredArgsConstructor
@Log4j2
public class ProductsCustomRepositoryImpl implements ProductsCustomRepository {

    private final MongoTemplate mongoTemplate;
    private final String COLLECTION_NAME = "products";

    @Override
    public ProductsPageRes getProductsListByCategory(ObjectId cursor, Pageable pageable, String mainCategory, String subCategory) {
        Query query = new Query().addCriteria(Criteria.where("id").gt(cursor))
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
                .collect(Collectors.toList());
        boolean hasNext = mongoTemplate.count(query, Products.class) >= pageable.getPageSize();
        var nextCursor = hasNext ? productsList.get(productsList.size() - 1).id() : null;
        productsList.remove(productsList.size() - 1);
        return ProductsPageRes.builder()
                .content(productsList)
                .nextCursor(nextCursor)
                .build();
    }

    @Override
    public ProductsPageRes findByProductName(String name, String cursor, Pageable pageable) {
        Query query = new Query().addCriteria(Criteria.where("name").regex("phone", "i"));
        log.info(name);
        if (!isNull(cursor) && !cursor.isEmpty()) {
            query.addCriteria(Criteria.where("id").gt(new ObjectId(cursor)));
        }

        List<ProductsListDto> productsList = mongoTemplate.find(query, Products.class)
                .stream()
                .map(ProductsListDto::toDto)
                .toList();
        boolean hasNext = mongoTemplate.count(query, Products.class) > ((pageable.getPageNumber() + 1) * pageable.getPageSize());
        var nextCursor = hasNext ? productsList.get(productsList.size() - 1).id() : null;

        if (productsList.size() > 1) {
            productsList.remove(productsList.size() - 1);
        }
        return ProductsPageRes.builder()
                .content(productsList)
                .nextCursor(nextCursor)
                .build();
    }

    @Override
    public ProductsPageRes findByKeywordList(List<String> keywords, String cursor, Pageable pageable) {
        Query query = new Query().addCriteria(Criteria.where("keywords").in(keywords)).with(pageable);
        log.info(keywords);
        if (!isNull(cursor) && !cursor.isEmpty()) {
            query.addCriteria(Criteria.where("id").gt(new ObjectId(cursor)));
        }

        List<ProductsListDto> productsList = mongoTemplate.find(query, Products.class)
                .stream()
                .map(ProductsListDto::toDto)
                .collect(Collectors.toList());

        if (productsList.isEmpty()) {
            return ProductsPageRes.builder()
                    .content(List.of())
                    .nextCursor(null)
                    .build();
        }

        boolean hasNext = mongoTemplate.count(query, Products.class) > ((pageable.getPageNumber() + 1) * pageable.getPageSize());
        var nextCursor = hasNext ? productsList.get(productsList.size() - 1).id() : null;
        productsList.remove(productsList.size() - 1);

        return ProductsPageRes.builder()
                .content(productsList)
                .nextCursor(nextCursor)
                .build();
    }

    @Override
    public ProductsDetailDto getProductDetailWithLimitedReviews(String id) {
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
        Products result = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, Products.class).getUniqueMappedResult();
        return ProductsDetailDto.toDto(result);
    }

    @Override
    public Page<ReviewObject> getReviews(String id, Pageable pageable) {
        AggregationOperation matchOperation = Aggregation.match(Criteria.where("_id").is(id));
        AggregationOperation sliceOperation = Aggregation.project()
                .and("reviews.count").as("reviews.count")
                .and("reviews.average_point").as("reviews.average_point")
                .and("reviews.reviews").slice(pageable.getPageSize(), (int) (pageable.getOffset()))
                .as("reviews.reviews");
        TypedAggregation<Products> aggregation = newAggregation(Products.class, matchOperation, sliceOperation);
        AggregationResults<Products> result = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, Products.class);
        Reviews reviews = Objects.requireNonNull(result.getUniqueMappedResult()).getReview();
        return new PageImpl<>(reviews.getReviewList(), pageable, reviews.getCount());
    }

    @Override
    public Page<ProductsListDto> getProductsWithManyReviews(int page, int size) {
        page = page > 9 ? 9 : Math.max(page, 0);
        Long maxProducts = 100L;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "reviews.count"));
        Query query = new Query().with(pageable);
        List<ProductsListDto> productsList = mongoTemplate.find(query, Products.class).stream()
                .map(ProductsListDto::toDto).toList();

        return new PageImpl<>(productsList, pageable, maxProducts);
    }

    @Override
    public Page<ProductImgRes> getProductImg(Pageable pageable, String mainCategory, String subCategory) {
        System.out.println(mainCategory);
        Query query = new Query().addCriteria(Criteria.where("crop").exists(true))
                .with(pageable);
        if (!isNull(mainCategory)) {
            query.addCriteria(Criteria.where("main_category").is(mainCategory));
        }
        if (!isNull(subCategory)) {
            query.addCriteria(Criteria.where("sub_category").is(subCategory));
        }
        log.info(query);
        var result = mongoTemplate.find(query, Products.class);
        List<ProductImgRes> productsList = result
                .stream()
                .map(ProductImgRes::toDto)
                .toList();

        return new PageImpl<>(productsList, pageable, result.size());
    }

    @Override
    public List<Products> findByKeywordsWithLimit(List<String> keywords) {
        Query query = new Query(Criteria.where("keywords").in(keywords)).limit(5);
        return mongoTemplate.find(query, Products.class);
    }
}
