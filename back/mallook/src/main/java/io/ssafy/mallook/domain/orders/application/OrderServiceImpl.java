package io.ssafy.mallook.domain.orders.application;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.orders.dao.OrderRepository;
import io.ssafy.mallook.domain.orders.dto.request.OrderCreateDto;
import io.ssafy.mallook.domain.orders.dto.request.OrderDeleteDto;
import io.ssafy.mallook.domain.orders.dto.response.OrderDetailDto;
import io.ssafy.mallook.domain.orders.dto.response.OrderListDto;
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
        // todo : 주문 상품 (product history) 저장
        // todo : 회원 등급별 할인율 적용
        // todo: 쿠폰 선택시 쿠폰 적용
    }

    @Override
    @Transactional
    public void deletedOrder(OrderDeleteDto orderDeleteDto) {
        orderRepository.deleteOrder(orderDeleteDto.deleteList());
        // todo: 주문 상품 (product history) 삭제
    }

    @Override
    public Long findMaxOrderId() {
        return orderRepository.findMaxOrderId();
    }


}
