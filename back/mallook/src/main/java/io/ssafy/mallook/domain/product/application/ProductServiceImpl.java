package io.ssafy.mallook.domain.product.application;

import io.ssafy.mallook.domain.product.dao.jpa.ProductCustomRepository;
import io.ssafy.mallook.domain.product.dao.jpa.ProductRepository;
import io.ssafy.mallook.domain.product.dao.mongo.ProductsCustomRepository;
import io.ssafy.mallook.domain.product.dao.mongo.ProductsRepository;
import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsDetail;
import io.ssafy.mallook.domain.product.dto.response.ProductsListDto;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Products;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Slice<ProductsListDto> getMongoProductsList(ObjectId cursor, Pageable pageable, String mainCategory, String subCategory) {
        return productsCustomRepository.getProductsListByCategory(cursor, pageable, mainCategory, subCategory);
    }

    @Override
    public Products getProductDetail(String id) {
        System.out.println(id);
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(productsCustomRepository.getProductDetailWithFirstReviews(id));
        var result =  mongoProductsRepository.findById(id)
                .orElseThrow(()-> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
//        ProductsDetail productsDetail = ProductsDetail.builder()
//                .id(result.getId())
//                .
//                .build();
//        System.out.println("#######################" + result.getReviews());
        return result;
    }


}
