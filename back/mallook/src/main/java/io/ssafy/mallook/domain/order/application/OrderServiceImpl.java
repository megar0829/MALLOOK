package io.ssafy.mallook.domain.order.application;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.order.dao.OrderRepository;
import io.ssafy.mallook.domain.order.dto.request.OrderCreateDto;
import lombok.RequiredArgsConstructor;
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
    @Transactional
    public void createOrder(UUID id, OrderCreateDto createDto) {
        Member proxyMember = memberRepository.getReferenceById(id);
        orderRepository.save(createDto.toEntity(proxyMember));
    }
}
