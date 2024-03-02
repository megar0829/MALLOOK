package io.ssafy.mallook.domain.order.api;

import io.ssafy.mallook.domain.order.application.OrderService;
import io.ssafy.mallook.domain.order.dto.request.OrderCreateDto;
import io.ssafy.mallook.domain.order.dto.request.OrderDeleteDto;
import io.ssafy.mallook.domain.order.dto.response.OrderListDto;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderListDto> getOrderList(@AuthenticationPrincipal UserSecurityDTO principal,
                                           @PageableDefault(size = 2,
                                                   sort = "createdAt",
                                                   direction = Sort.Direction.DESC) Pageable pageable) {
        UUID id = principal.getId();
        return orderService.getOrderList(id, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createOrder(@AuthenticationPrincipal UserSecurityDTO principal,
                            @RequestBody @Valid OrderCreateDto createDto) {
        UUID id = principal.getId();
        orderService.createOrder(id, createDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("@authService.authorizeToDeleteOrder(#principal.getId(), #orderDeleteDto)")
    public void deleteOrder(@AuthenticationPrincipal UserSecurityDTO principal,
                            @RequestBody @Valid OrderDeleteDto orderDeleteDto) {
        orderService.deletedOrder(orderDeleteDto);
    }
}
