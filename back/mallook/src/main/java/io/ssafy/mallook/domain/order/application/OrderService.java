package io.ssafy.mallook.domain.order.application;

import io.ssafy.mallook.domain.order.dto.request.OrderCreateDto;
import io.ssafy.mallook.domain.order.dto.request.OrderDeleteDto;
import io.ssafy.mallook.domain.order.dto.response.OrderListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderService {

    Page<OrderListDto> getOrderList(UUID id, Pageable pageable);

    void createOrder(UUID id, OrderCreateDto createDto);

    void deletedOrder(OrderDeleteDto orderDeleteDto);
}
