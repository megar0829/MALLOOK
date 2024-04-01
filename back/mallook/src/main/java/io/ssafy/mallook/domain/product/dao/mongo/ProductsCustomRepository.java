package io.ssafy.mallook.domain.product.dao.mongo;

import io.ssafy.mallook.domain.product.dto.request.ProductHotKeywordDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsDetailDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsListDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsPageRes;
import io.ssafy.mallook.domain.product.entity.ReviewObject;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsCustomRepository {

    Slice<ProductsListDto> findByProductName(String name, String cursor, Pageable pageable);

    Slice<ProductsListDto> findByKeywordList(ProductHotKeywordDto hotKeywordDto, String cursor, Pageable pageable);

    ProductsPageRes getProductsListByCategory(ObjectId cursor, Pageable pageable, String mainCategory, String subCategory);

    ProductsDetailDto getProductDetailWithLimitedReviews(String id);

    Page<ReviewObject> getReviews(String id, Pageable pageable);
    Page<ProductsListDto> getProductsWithManyReviews(int page, int size);
}
