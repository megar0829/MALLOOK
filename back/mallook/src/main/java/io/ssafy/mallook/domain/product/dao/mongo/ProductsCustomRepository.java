package io.ssafy.mallook.domain.product.dao.mongo;

import io.ssafy.mallook.domain.product.dto.response.ProductImgRes;
import io.ssafy.mallook.domain.product.dto.response.ProductsDetailDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsListDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsPageRes;
import io.ssafy.mallook.domain.product.entity.Products;
import io.ssafy.mallook.domain.product.entity.ReviewObject;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsCustomRepository {

    ProductsPageRes findByProductName(String name, String cursor, Pageable pageable);

    ProductsPageRes findByKeywordList(List<String> keywords, String cursor, Pageable pageable);

    ProductsPageRes getProductsListByCategory(ObjectId cursor, Pageable pageable, String mainCategory, String subCategory);

    ProductsDetailDto getProductDetailWithLimitedReviews(String id);

    Page<ReviewObject> getReviews(String id, Pageable pageable);

    Page<ProductsListDto> getProductsWithManyReviews(int page, int size);

    Page<ProductImgRes> getProductImg(Pageable pageable, String mainCategory, String subCategory);

    List<Products> findByKeywordsWithLimit(List<String> keywords);
}
