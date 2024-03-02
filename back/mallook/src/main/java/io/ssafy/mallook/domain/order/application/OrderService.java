package io.ssafy.mallook.domain.order.application;

import io.ssafy.mallook.domain.order.dto.request.OrderCreateDto;

import java.util.UUID;

public interface OrderService {
    void createOrder(UUID id, OrderCreateDto createDto);
}
