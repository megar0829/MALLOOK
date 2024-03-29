package io.ssafy.mallook.domain.product.api;

import io.ssafy.mallook.domain.product.application.ProductService;
import io.ssafy.mallook.domain.product.dto.response.ProductListDto;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<BaseResponse<Page<ProductListDto>>> getProductList(@PageableDefault(
            size = 20,
            sort = "name",
            direction = Sort.Direction.ASC
    ) Pageable pageable
            , @RequestParam(name = "primary", required = false) String mainCategory
            , @RequestParam(name = "secondary",required = false) String sumCategory
    ) {

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                productService.getProductList(pageable, mainCategory.toUpperCase(), sumCategory.toUpperCase())
        );
    }
}
