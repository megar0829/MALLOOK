package io.ssafy.mallook.domain.order.application;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.order.dao.OrderRepository;
import io.ssafy.mallook.domain.order.dto.request.OrderCreateDto;
import io.ssafy.mallook.domain.order.dto.request.OrderDeleteDto;
import io.ssafy.mallook.domain.order.dto.response.OrderDetailDto;
import io.ssafy.mallook.domain.order.dto.response.OrderListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<OrderListDto> getOrderList(UUID id, Pageable pageable) {
        Member proxyMember = memberRepository.getReferenceById(id);

        return orderRepository.findAllByMember(proxyMember, pageable)
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
    public void deletedOrder(OrderDeleteDto orderDeleteDto) {
        orderRepository.deleteOrder(orderDeleteDto.deleteList());
    }
}
