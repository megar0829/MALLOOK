package io.ssafy.mallook.domain.order.api;

import io.ssafy.mallook.domain.order.application.OrderService;
import io.ssafy.mallook.domain.order.dto.request.OrderCreateDto;
import io.ssafy.mallook.domain.order.dto.request.OrderDeleteDto;
import io.ssafy.mallook.domain.order.dto.response.OrderDetailDto;
import io.ssafy.mallook.domain.order.dto.response.OrderListDto;
import io.ssafy.mallook.global.common.BaseResponse;
import io.ssafy.mallook.global.common.code.SuccessCode;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<BaseResponse<Slice<OrderListDto>>> getOrderList
            (@AuthenticationPrincipal UserSecurityDTO principal,
             @PageableDefault(size = 20,
                     sort = "id",
                     direction = Sort.Direction.DESC) Pageable pageable,
             @RequestParam(required = false) Long cursor) {
        UUID id = principal.getId();
        cursor = cursor != null ? cursor : orderService.findMaxOrderId();

        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                orderService.getOrderList(cursor, id, pageable)
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("@authService.authorizeToReadOrderDetail(#principal.getId(), #id)")
    public ResponseEntity<BaseResponse<OrderDetailDto>> getOrderDetail(@AuthenticationPrincipal UserSecurityDTO principal,
                                                                       @PathVariable Long id) {
        return BaseResponse.success(
                SuccessCode.SELECT_SUCCESS,
                orderService.getOrderDetail(id)
        );
    }

    @PostMapping
    public ResponseEntity<BaseResponse<String>> createOrder(@AuthenticationPrincipal UserSecurityDTO principal,
                                                            @RequestBody @Valid OrderCreateDto createDto) {
        UUID id = principal.getId();
        orderService.createOrder(id, createDto);
        return BaseResponse.success(
                SuccessCode.INSERT_SUCCESS,
                "성공적으로 주문되었습니다."
        );
    }

    @DeleteMapping
    @PreAuthorize("@authService.authorizeToDeleteOrder(#principal.getId(), #orderDeleteDto)")
    public ResponseEntity<BaseResponse<String>> deleteOrder(@AuthenticationPrincipal UserSecurityDTO principal,
                                                            @RequestBody @Valid OrderDeleteDto orderDeleteDto) {
        orderService.deletedOrder(orderDeleteDto);
        return BaseResponse.success(
                SuccessCode.DELETE_SUCCESS,
                "성공적으로 삭제되었습니다."
        );
    }
}
