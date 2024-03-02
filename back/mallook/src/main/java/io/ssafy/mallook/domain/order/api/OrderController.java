package io.ssafy.mallook.domain.order.api;

import io.ssafy.mallook.domain.order.application.OrderService;
import io.ssafy.mallook.domain.order.dto.request.OrderCreateDto;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createOrder(@AuthenticationPrincipal UserSecurityDTO principal,
                            @RequestBody @Valid OrderCreateDto createDto) {
        UUID id = principal.getId();
        orderService.createOrder(id, createDto);
    }
}
