package io.ssafy.mallook.domain.orders.application;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.orders.dao.OrderRepository;
import io.ssafy.mallook.domain.orders.dto.request.OrderCreateDto;
import io.ssafy.mallook.domain.orders.dto.request.OrderDeleteDto;
import io.ssafy.mallook.domain.orders.dto.request.OrderInsertReq;
import io.ssafy.mallook.domain.orders.dto.response.OrderDetailDto;
import io.ssafy.mallook.domain.orders.dto.response.OrderListDto;
import io.ssafy.mallook.domain.orders.entity.Orders;
import io.ssafy.mallook.domain.product.dao.mongo.ProductsRepository;
import io.ssafy.mallook.domain.product.entity.Products;
import io.ssafy.mallook.domain.product_history.dao.ProductHistoryRepository;
import io.ssafy.mallook.domain.product_history.entity.ProductHistory;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductHistoryRepository productHistoryRepository;
    private final ProductsRepository productsRepository;

    @Override
    public Slice<OrderListDto> getOrderList(Long cursor, UUID id, Pageable pageable) {
        Member proxyMember = memberRepository.getReferenceById(id);

        return orderRepository.findByIdLessThanAndMemberOrderByIdDesc(cursor, proxyMember, pageable)
                .map(OrderListDto::toDto);
    }

    @Override
    public OrderDetailDto getOrderDetail(Long id) {
        return orderRepository.findById(id)
                .map(OrderDetailDto::toDto)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void createOrder(UUID id, OrderCreateDto createDto) {
        Member proxyMember = memberRepository.getReferenceById(id);
        orderRepository.save(createDto.toEntity(proxyMember));
    }

    @Override
    @Transactional
    public void insertOrder(UUID id, OrderInsertReq createDtoList) {
        Member proxyMember = memberRepository.getReferenceById(id);
        // order 저장
        var order = orderRepository.save(Orders.builder()
                .totalFee(createDtoList.totalFee())
                .totalCount(createDtoList.totalCount())
                .totalPrice(createDtoList.totalPrice())
                .member(proxyMember)
                .build());
        // producthistory 저장, 장바구니 삭제
        createDtoList.products().forEach((dto) -> {
            Products product = productsRepository.findById(dto.productId())
                    .orElseThrow(() -> new BaseExceptionHandler(ErrorCode.NOT_FOUND_ERROR));
            productHistoryRepository.save(ProductHistory.builder()
                    .productCount(dto.count())
                    .productPrice(dto.price())
                    .productName(product.getName())
                    .productImage(product.getImage())
                    .productSize(dto.size())
                    .productColor(dto.color())
                    .orders(order)
                    .build());
        });

    }

    @Override
    @Transactional
    public void deletedOrder(OrderDeleteDto orderDeleteDto) {
        orderRepository.deleteOrder(orderDeleteDto.deleteList());
    }

    @Override
    @Transactional
    public void removeOrder(OrderDeleteDto orderDeleteDto) {
        orderRepository.deleteOrder(orderDeleteDto.deleteList());
        productHistoryRepository.deleteProductHistory(orderDeleteDto.deleteList());
    }


    @Override
    public Long findMaxOrderId() {
        return orderRepository.findMaxOrderId();
    }


}
