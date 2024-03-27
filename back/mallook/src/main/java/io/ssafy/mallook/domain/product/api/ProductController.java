package io.ssafy.mallook.domain.product.api;

import io.ssafy.mallook.domain.product.application.ProductService;
import io.ssafy.mallook.domain.product.dto.request.ProductHotKeywordDto;
import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsDetailDto;
import io.ssafy.mallook.domain.product.dto.response.ProductsListDto;
import io.ssafy.mallook.domain.product.entity.MainCategory;
import io.ssafy.mallook.domain.product.entity.Products;
import io.ssafy.mallook.domain.product.entity.SubCategory;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Supplier;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        cursor = cursor != null ? cursor : productService.getLastMongoProductsId();
        ObjectId cursorObjectId = new ObjectId(cursor);
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                productService.getMongoProductsList(cursorObjectId, pageable, mainCategory, subCategory)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<Slice<ProductsListDto>>> getProductDetail(
            @RequestParam String name,
            @RequestParam(required = false) String cursor,
            @RequestBody ProductHotKeywordDto hotKeywordDto) {
        cursor = cursor != null ? cursor : productService.getLastMongoProductsId();
        String finalCursor = cursor;
        Supplier<Slice<ProductsListDto>> methodToCall = (hotKeywordDto.hotKeywordList() == null || hotKeywordDto.hotKeywordList().isEmpty())
                ? () -> productService.getProductDetail(name, finalCursor)
                : () -> productService.getProductDetail(hotKeywordDto, finalCursor);

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                methodToCall.get()
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
        cursor = cursor != null ? cursor : productService.getLastProductId() + 1;
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
