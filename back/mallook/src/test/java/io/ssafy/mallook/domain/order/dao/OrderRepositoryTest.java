package io.ssafy.mallook.domain.order.dao;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.order.entity.Orders;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EntityManager entityManager;

    private Orders orders;

    @BeforeEach
    void setUp() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
        orders = buildOrders(member);
        entityManager.flush();
        entityManager.clear();

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
    void findAllByMember() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
        Pageable pageable = PageRequest.of(0, 2);

        Orders orders1 = buildOrders(member);
        Orders orders2 = buildOrders(member);
        orderRepository.save(orders1);
        orderRepository.save(orders2);
//        entityManager.flush();
//        entityManager.clear();

        // findAllByMember 메서드 호출
        Page<Orders> result = orderRepository.findAllByMember(member, pageable);

        // 검증
        assertThat(result).isNotNull();
        assertThat(result.getContent()).contains(orders1, orders2);
    }

    @Test
    void deleteOrder() {
        Member member = Mockito.mock(Member.class);
        memberRepository.save(member);
        List<Long> deleteList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Orders orders = buildOrders(member);
            orderRepository.save(orders);
            deleteList.add(orders.getId());
        }

        orderRepository.deleteOrder(deleteList);

        for (Long id : deleteList) {
            Optional<Orders> optionalOrders = orderRepository.findById(id);
            assertThat(optionalOrders.isPresent()).isFalse();
        }
    }
}