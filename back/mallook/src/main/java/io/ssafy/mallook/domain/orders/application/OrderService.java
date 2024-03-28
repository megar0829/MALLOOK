package io.ssafy.mallook.domain.orders.application;

import io.ssafy.mallook.domain.orders.dto.request.OrderCreateDto;
import io.ssafy.mallook.domain.orders.dto.request.OrderDeleteDto;
import io.ssafy.mallook.domain.orders.dto.request.OrderInsertReq;
import io.ssafy.mallook.domain.orders.dto.response.OrderDetailDto;
import io.ssafy.mallook.domain.orders.dto.response.OrderListDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface OrderService {

    Slice<OrderListDto> getOrderList(Long cursor, UUID id, Pageable pageable);

    OrderDetailDto getOrderDetail(Long id);

    void createOrder(UUID id, OrderCreateDto createDto);
    void insertOrder(UUID id, OrderInsertReq orderCreateReq);

    void deletedOrder(OrderDeleteDto orderDeleteDto);
    void removeOrder(OrderDeleteDto orderDeleteDto);

    Long findMaxOrderId();
}
