package io.ssafy.mallook.domain.product.dao;

import io.ssafy.mallook.domain.product.entity.Product;

import java.util.List;

public interface ProductCustomRepository {

    List<Product> findAllProduct(String genderCategory,
                                 String mainCategory,
                                 String subCategory);
}
