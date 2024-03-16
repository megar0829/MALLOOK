package io.ssafy.mallook.domain.order.application;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.order.dao.OrderRepository;
import io.ssafy.mallook.domain.order.dto.request.OrderCreateDto;
import io.ssafy.mallook.domain.order.dto.request.OrderDeleteDto;
import io.ssafy.mallook.domain.order.dto.response.OrderDetailDto;
import io.ssafy.mallook.domain.order.dto.response.OrderListDto;
import io.ssafy.mallook.domain.order.entity.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Member member;

    private Orders orders;

    @BeforeEach
    void setUp() {
        member = Mockito.mock(Member.class);
        memberRepository.save(member);
        orders = buildOrders(member);
    }

    private Orders buildOrders(Member member) {
        return Orders.builder()
                .totalPrice(1000L)
                .totalFee(500L)
                .totalCount(2L)
                .member(member)
                .build();
    }

    @Test
    void getOrderList() {
        UUID id = member.getId();
        boolean hasNext = false;
        Pageable pageable = PageRequest.of(0, 2);
        Member proxyMember = memberRepository.getReferenceById(id);
        Long cursor = 21L;
        List<Orders> list = new ArrayList<>();

        Slice<Orders> emptyPage = new SliceImpl<>(list, pageable, hasNext);
        Mockito.when(orderRepository.findByIdLessThanAndMemberOrderByIdDesc(cursor, proxyMember, pageable))
                .thenReturn(emptyPage);
        orderService.getOrderList(cursor, id, pageable);

        Mockito.verify(orderRepository, Mockito.times(1)).findByIdLessThanAndMemberOrderByIdDesc(cursor, proxyMember, pageable);
        Mockito.verify(memberRepository, Mockito.times(2)).getReferenceById(id);
    }

    @Test
    void getOrderDetail() {
        Long id = orders.getId();

        Mockito.when(orderRepository.findById(id)).thenReturn(Optional.of(orders));
        OrderDetailDto result = orderService.getOrderDetail(id);

        Mockito.verify(orderRepository, Mockito.times(1)).findById(id);
        assertThat(result).isNotNull();
    }

    @Test
    void createOrder() {
        UUID id = member.getId();
        OrderCreateDto orderCreateDto = new OrderCreateDto(1000L, 1L, 10L);

        orderService.createOrder(id, orderCreateDto);

        Mockito.verify(orderRepository, Mockito.times(1)).save(Mockito.any(Orders.class));
    }

    @Test
    void deletedOrder() {
        List<Long> deleteList = new ArrayList<>();
        OrderDeleteDto orderDeleteDto = new OrderDeleteDto(deleteList);

        orderService.deletedOrder(orderDeleteDto);

        Mockito.verify(orderRepository, Mockito.times(1)).deleteOrder(Mockito.anyList());
    }
}