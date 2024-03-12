package io.ssafy.mallook.domain.shoppingmall.api;

import io.ssafy.mallook.domain.shoppingmall.application.ShoppingMallService;
import io.ssafy.mallook.domain.shoppingmall.dto.response.ShoppingMallListDto;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shopping-mall")
public class ShoppingMallController {

    private final ShoppingMallService shoppingMallService;

    @GetMapping
    public ResponseEntity<BaseResponse<Page<ShoppingMallListDto>>> getAllShoppingMallList(
            @PageableDefault(
            size = 20,
            sort = "name", direction =
            Sort.Direction.ASC) Pageable pageable) {
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                shoppingMallService.getAllMallList(pageable)
        );
    }
}
