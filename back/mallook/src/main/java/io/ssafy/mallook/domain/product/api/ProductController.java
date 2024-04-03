package io.ssafy.mallook.domain.product.api;

import com.amazonaws.util.StringUtils;
import io.ssafy.mallook.domain.product.application.ProductService;
import io.ssafy.mallook.domain.product.dto.response.*;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Products;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Supplier;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.amazonaws.util.CollectionUtils.isNullOrEmpty;
import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Log4j2
@Tag(name = "상품", description = "상품 관련 API")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 리스트 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "상품 리스트 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "상품 리스트 조회 실패")
            })
    @GetMapping
    public ResponseEntity<BaseResponse<ProductsPageRes>> getProductsList(
            @PageableDefault(size = 20,
                    sort = "_id",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) String cursor,
            @RequestParam(name = "primary", required = false, defaultValue = "상의") String mainCategory,
            @RequestParam(name = "secondary", required = false) String subCategory
    ) {
//        cursor = !StringUtils.isNullOrEmpty(cursor) ? cursor : productService.getLastMongoProductsId();
        cursor = !StringUtils.isNullOrEmpty(cursor) ? cursor : productService.getLastMongoProductsId();

        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize() + 1, Sort.by(Sort.Direction.DESC, "reviews.count"));
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                productService.getMongoProductsList(new ObjectId(cursor), pageable, mainCategory, subCategory)
        );
    }

    @Operation(
            summary = "리뷰순 top100 상품 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "리뷰순 top100 상품 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "리뷰순 top100 상품 조회 실패")
            })
    @GetMapping("/popular")
    public ResponseEntity<BaseResponse<ProductPageRes>> getProductsListWithManyReviews(
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                productService.getProductsWithManyReviews(pageable)
        );
    }

    @Operation(summary = "상품 검색",
            responses = {
                    @ApiResponse(responseCode = "200", description = "상품 검색 성공"),
                    @ApiResponse(responseCode = "404", description = "상품 검색 실패")
            })
    @GetMapping("/search")
    public ResponseEntity<BaseResponse<ProductsPageRes>> getProductDetail(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String cursor,
            @RequestParam(required = false) List<String> keywords,
            @PageableDefault(size = 20,
                    sort = "id",
                    direction = Sort.Direction.DESC) Pageable pageable
    ) {
        if (isNull(name) && isNullOrEmpty(keywords)) {
            throw new NullPointerException("Both name and keywords are null or empty");
        }

        cursor = !isNull(cursor) ? cursor : productService.getLastMongoProductsId();
        final Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize() + 1);
        String finalCursor = cursor;
        log.info(cursor);
        Supplier<ProductsPageRes> methodToCall = isNullOrEmpty(keywords)
                ? () -> productService.getProductDetail(name, finalCursor, page)
                : () -> productService.getProductDetail(keywords, finalCursor, page);

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                methodToCall.get()
        );
    }

    @Operation(summary = "서브 카테고리 별 추천상품 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "상품 상세 정보 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "상품 상세 정보 조회 실패")
            })
    @GetMapping("/category-recommend")
    public ResponseEntity<BaseResponse<ProductPageRes>> getRecommendedProducts(
            @RequestParam("secondary") @NotBlank String subCategory
    ) {
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                productService.getRecommendedProducts(subCategory)
        );
    }


    @Operation(summary = "상품 상세 정보 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "상품 상세 정보 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "상품 상세 정보 조회 실패")
            })
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductsDetailDto>> getProductsDetail(@PathVariable("id") String id) {
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                productService.getMongoProductsDetail(id)
        );
    }

    @Operation(summary = "리뷰 다음 페이지 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "리뷰 다음 페이지 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "리뷰 다음 페이지 조회 실패")
            })
    @GetMapping("/reviews")
    public ResponseEntity<BaseResponse<ReviewPageRes>> getReviewList(
            @RequestParam(name = "id") String id,
            @PageableDefault(size = 20, direction = Sort.Direction.DESC) Pageable pageable) {
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                productService.getReviewList(id, pageable)
        );
    }


    @GetMapping("/mysql")
    public ResponseEntity<BaseResponse<Slice<ProductListDto>>> getProductList(
            @PageableDefault(size = 20,
                    sort = "id",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) Long cursor,
            @RequestParam(name = "primary", required = false) MainCategory mainCategory,
            @RequestParam(name = "secondary", required = false) SubCategory subCategory
    ) {
        cursor = !isNull(cursor) ? cursor : productService.getLastProductId() + 1;
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                productService.getProductList(cursor, pageable, mainCategory, subCategory)
        );
    }

    @GetMapping("/mongotest")
    public ResponseEntity<BaseResponse<Slice<Products>>> getProduct(
            @PageableDefault(size = 20,
                    sort = "id",
                    direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var result = productService.findByName(pageable);
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                result
        );
    }
}
