package io.ssafy.mallook.domain.product.application;

import io.ssafy.mallook.domain.product.dao.jpa.ProductCustomRepository;
import io.ssafy.mallook.domain.product.dao.jpa.ProductRepository;
import io.ssafy.mallook.domain.product.dao.mongo.ProductsCustomRepository;
import io.ssafy.mallook.domain.product.dao.mongo.ProductsRepository;
import io.ssafy.mallook.domain.product.dto.request.ProductHotKeywordDto;
import io.ssafy.mallook.domain.product.dto.response.*;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Products;
import io.ssafy.mallook.domain.product.entity.ReviewObject;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCustomRepository productCustomRepository;

    private final ProductsRepository mongoProductsRepository;
    private final ProductsCustomRepository productsCustomRepository;

    @Override
    public Slice<ProductListDto> getProductList(Long cursor, Pageable pageable, MainCategory mainCategory, SubCategory subCategory) {
        return productCustomRepository.findAllProduct(cursor, pageable, mainCategory, subCategory);
    }

    @Override
    public Slice<ProductsListDto> getProductDetail(String name, String cursor, Pageable pageable) {
        return productsCustomRepository.findByProductName(name, cursor, pageable);
    }

    @Override
    public Slice<ProductsListDto> getProductDetail(ProductHotKeywordDto hotKeywordDto, String cursor, Pageable pageable) {
        return productsCustomRepository.findByKeywordList(hotKeywordDto, cursor, pageable);
    }

    @Override
    public Long getLastProductId() {
        return productRepository.findMaxId();
    }

    @Override
    public Slice<Products> findByName(Pageable name) {
//        var rst = mongoProductsRepository.findFirstByOrderByIdDesc();
        return mongoProductsRepository.findAll(name);
    }

    @Override
    public String getLastMongoProductsId() {
        return mongoProductsRepository.findFirstByOrderByIdDesc().getId().toString();
    }

    @Override
    public ProductsPageRes getMongoProductsList(ObjectId cursor, Pageable pageable, String mainCategory, String subCategory) {
        return productsCustomRepository.getProductsListByCategory(cursor, pageable, mainCategory, subCategory);
    }

    @Override
    public ProductsDetailDto getMongoProductsDetail(String id) {
        var result = productsCustomRepository.getProductDetailWithLimitedReviews(id);
        if (Objects.isNull(result)) {
            throw new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR);
        }
        return result;
    }

    @Override
    public ReviewPageRes getReviewList(String productsId, Pageable pageable) {
        var result = productsCustomRepository.getReviews(productsId, pageable);
        return new ReviewPageRes(result.getContent(), result.getNumber(), result.getTotalPages());
    }

    @Override
    public ProductPageRes getProductsWithManyReviews(Pageable pageable) {
        var result = productsCustomRepository.getProductsWithManyReviews(pageable.getPageNumber(), pageable.getPageSize());
        return new ProductPageRes(result.getContent(), result.getNumber(), result.getTotalPages());
    }
}
