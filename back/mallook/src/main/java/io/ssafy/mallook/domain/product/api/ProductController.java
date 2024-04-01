package io.ssafy.mallook.domain.product.api;

import com.amazonaws.util.CollectionUtils;
import io.ssafy.mallook.domain.product.application.ProductService;
import io.ssafy.mallook.domain.product.dto.request.ProductHotKeywordDto;
import io.ssafy.mallook.domain.product.dto.response.*;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Products;
import io.ssafy.mallook.domain.product.entity.ReviewObject;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.ErrorResponse;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Supplier;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.amazonaws.util.CollectionUtils.*;
import static io.ssafy.mallook.global.common.code.ErrorCode.*;
import static io.ssafy.mallook.global.common.code.ErrorCode.BAD_REQUEST_ERROR;
import static java.util.Objects.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
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
            @PageableDefault(size = 21,
                    sort = "id",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) String cursor,
            @RequestParam(name = "primary", required = false) String mainCategory,
            @RequestParam(name = "secondary", required = false) String subCategory
    ) {
        cursor = !isNull(cursor) ? cursor : productService.getLastMongoProductsId();
        ObjectId cursorObjectId = new ObjectId(cursor);
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                productService.getMongoProductsList(cursorObjectId, pageable, mainCategory, subCategory)
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
    public ResponseEntity<?> getProductDetail(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String cursor,
            @RequestParam(required = false) ProductHotKeywordDto hotKeywordDto,
            @PageableDefault(size = 20,
                    sort = "id",
                    direction = Sort.Direction.DESC) Pageable pageable
    ) {
        if (isNull(name) && isNull(hotKeywordDto)) {
            List<FieldError> errors = new ArrayList<>();
            FieldError fieldError = new FieldError("검색어", "name", "FAIL");
            errors.add(fieldError);
            ErrorResponse errorResponse = ErrorResponse.of()
                    .code(BAD_REQUEST_ERROR)
                    .message("검색어를 입력해야 합니다.")
                    .errors(errors)
                    .build();

            return ResponseEntity
                    .badRequest()
                    .body(errorResponse);
        }

        cursor = !isNull(cursor) ? cursor : productService.getLastMongoProductsId();
        String finalCursor = cursor;
        Supplier<Slice<ProductsListDto>> methodToCall = (isNull(hotKeywordDto) || isNullOrEmpty(hotKeywordDto.hotKeywordList()))
                ? () -> productService.getProductDetail(name, finalCursor, pageable)
                : () -> productService.getProductDetail(hotKeywordDto, finalCursor, pageable);

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                methodToCall.get()
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
