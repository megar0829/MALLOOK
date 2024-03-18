package io.ssafy.mallook.domain.order.application;

import io.ssafy.mallook.domain.order.dto.request.OrderCreateDto;
import io.ssafy.mallook.domain.order.dto.request.OrderDeleteDto;
import io.ssafy.mallook.domain.order.dto.response.OrderDetailDto;
import io.ssafy.mallook.domain.order.dto.response.OrderListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface OrderService {

    Slice<OrderListDto> getOrderList(Long cursor, UUID id, Pageable pageable);

    OrderDetailDto getOrderDetail(Long id);

    void createOrder(UUID id, OrderCreateDto createDto);

    void deletedOrder(OrderDeleteDto orderDeleteDto);

    Long findMaxOrderId();
}
