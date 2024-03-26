package io.ssafy.mallook.domain.product.application;

import io.ssafy.mallook.domain.product.dao.jpa.ProductCustomRepository;
import io.ssafy.mallook.domain.product.dao.jpa.ProductRepository;
import io.ssafy.mallook.domain.product.dao.mongo.ProductsCustomRepository;
import io.ssafy.mallook.domain.product.dao.mongo.ProductsRepository;
import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Products;
import io.ssafy.mallook.domain.product.entity.SubCategory;
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
        var rst = mongoProductsRepository.findFirstByOrderByIdAsc();
        System.out.println("######################"+rst.getId());
//        var rsult = mongoProductsRepository.findAll();
//        System.out.println(rsult);
        return mongoProductsRepository.findAll(name);
    }

    @Override
    public String getLastMongoProductsId() {
        return mongoProductsRepository.findFirstByOrderByIdAsc().getId().toString();
    }

    @Override
    public Slice<Products> getMongoProductsList(ObjectId cursor, Pageable pageable, String mainCategory, String subCategory) {
//        return mongoProductsRepository.findProductsByMainCategoryAndSubCategoryAndIdGreaterThan(mainCategory, subCategory, cursor, pageable);
        return productsCustomRepository.findByCategory(cursor, pageable, mainCategory, subCategory);
    }
}
