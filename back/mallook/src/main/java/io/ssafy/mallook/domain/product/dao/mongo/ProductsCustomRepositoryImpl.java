package io.ssafy.mallook.domain.product.dao.mongo;

import io.ssafy.mallook.domain.product.dto.request.ProductHotKeywordDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsDetailDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsListDto;
import io.ssafy.mallook.domain.product.entity.Products;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductsCustomRepositoryImpl implements ProductsCustomRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Slice<ProductsListDto> findByCategory(ObjectId cursor, Pageable pageable, String mainCategory, String subCategory) {
        Query query = new Query().addCriteria(Criteria.where("id").lt(cursor))
                .with(pageable);

        if (mainCategory != null) {
            query.addCriteria(Criteria.where("mainCategory").is(mainCategory));
        }
        if (subCategory != null) {
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

        if (cursor != null && !cursor.isEmpty()) {
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

        if (cursor != null && !cursor.isEmpty()) {
            query.addCriteria(Criteria.where("id").lt(new ObjectId(cursor)));
        }

        List<ProductsListDto> productsList = mongoTemplate.find(query, Products.class)
                .stream()
                .map(ProductsListDto::toDto)
                .toList();
        boolean hasNext = mongoTemplate.count(query, Products.class) > ((pageable.getPageNumber() + 1) * pageable.getPageSize());

        return new SliceImpl<>(productsList, pageable, hasNext);
    }
}
