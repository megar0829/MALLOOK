package io.ssafy.mallook.domain.product.api;

import com.amazonaws.util.CollectionUtils;
import io.ssafy.mallook.domain.product.application.ProductService;
import io.ssafy.mallook.domain.product.dto.request.ProductHotKeywordDto;
import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsListDto;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Products;
import io.ssafy.mallook.domain.product.entity.ReviewObject;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.ErrorResponse;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.common.code.SuccessCode;
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
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<BaseResponse<Slice<ProductsListDto>>> getProductsList(
            @PageableDefault(size = 20,
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

    @GetMapping("/search")
    public ResponseEntity<?> getProductDetail(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String cursor,
            @RequestBody(required = false) ProductHotKeywordDto hotKeywordDto) {
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
                ? () -> productService.getProductDetail(name, finalCursor)
                : () -> productService.getProductDetail(hotKeywordDto, finalCursor);

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                methodToCall.get()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Products>> getProductsDetail(
            @PathVariable("id") String id) {
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                productService.getMongoProductsDetail(id)
        );
    }

    @GetMapping("/reviews")
    public ResponseEntity<BaseResponse<Page<ReviewObject>>> getReviewList(
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
