package io.ssafy.mallook.domain.product.application;

import io.ssafy.mallook.domain.product.dto.response.*;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Products;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.List;

public interface ProductService {
    Slice<ProductListDto> getProductList(Long cursor, Pageable pageable, MainCategory mainCategory, SubCategory subCategory);

    ProductsPageRes getProductDetail(String name, String cursor, Pageable pageable);

    ProductsPageRes getProductDetail(List<String> hotKeywordDto, String cursor, Pageable pageable);

    ProductsPageRes getMongoProductsList(ObjectId cursor, Pageable pageable, String mainCategory, String subCategory);

    ProductsDetailDto getMongoProductsDetail(String id);

    ReviewPageRes getReviewList(String productsId, Pageable pageable);

    ProductPageRes getProductsWithManyReviews(Pageable pageable);

    Long getLastProductId();

    Slice<Products> findByName(Pageable pageable);

    String getLastMongoProductsId();
    ProductPageRes getRecommendedProducts(String subCategory);

}
