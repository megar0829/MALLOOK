package io.ssafy.mallook.domain.cart.api;

import io.ssafy.mallook.domain.cart.application.CartService;
import io.ssafy.mallook.domain.cart.dto.request.CartInsertReq;
import io.ssafy.mallook.domain.cart.dto.request.CartProductDeleteReq;
import io.ssafy.mallook.domain.cart.dto.response.CartDetailRes;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "장바구니", description = "장바구니 관련 API")
public class CartController {
    private final CartService cartService;

    @Operation(summary = "장바구니 조회",
            responses = {
                    @ApiResponse(responseCode = "200", description = "장바구니 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "장바구니 조회 실패")
            })
    @GetMapping
    public ResponseEntity<BaseResponse<List<CartDetailRes>>> findProductsInCart(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO
    ) {
        var result = cartService.findProductsInCart(userSecurityDTO.getId());
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                result
        );
    }

    @Operation(summary = "장바구니 추가",
            responses = {
                    @ApiResponse(responseCode = "200", description = "장바구니 추가 성공"),
                    @ApiResponse(responseCode = "404", description = "장바구니 추가 실패")
            })
    @PostMapping
    public ResponseEntity<BaseResponse<String>> insertProductInCart(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @RequestBody CartInsertReq cartInsertReq) {
        cartService.insertProductInCart(userSecurityDTO.getId(), cartInsertReq);
        return BaseResponse.success(
                SuccessCode.INSERT_SUCCESS,
                "장바구니 상품 추가 성공"
        );
    }

    @Operation(summary = "장바구니 내 상품 삭제",
            responses = {
                    @ApiResponse(responseCode = "200", description = "장바구니 내 상품 삭제 성공"),
                    @ApiResponse(responseCode = "404", description = "장바구니 내 상품 삭제 실패")
            })
    @DeleteMapping
    public ResponseEntity<BaseResponse<String>> deleteProductInCart(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO,
            @RequestBody CartProductDeleteReq cartDeleteReq) {
        cartService.deleteProductInCart(userSecurityDTO.getId(), cartDeleteReq);
        return BaseResponse.success(
                SuccessCode.DELETE_SUCCESS,
                "장바구니 내 상품 삭제 성공"
        );
    }

    @Operation(summary = "장바구니 전체 삭제",
            responses = {
                    @ApiResponse(responseCode = "200", description = "장바구니 전체 삭제 성공"),
                    @ApiResponse(responseCode = "404", description = "장바구니 전체 삭제 실패")
            })
    @DeleteMapping("/all")
    public ResponseEntity<BaseResponse<String>> deleteCart(
            @AuthenticationPrincipal UserSecurityDTO userSecurityDTO) {
        cartService.deleteCart(userSecurityDTO.getId());
        return BaseResponse.success(
                SuccessCode.DELETE_SUCCESS,
                "장바구니 삭제 성공"
        );
    }
}
