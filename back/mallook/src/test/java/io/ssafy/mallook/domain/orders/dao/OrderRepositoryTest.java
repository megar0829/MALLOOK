package io.ssafy.mallook.domain.orders.dao;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.orders.entity.Orders;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles(profiles = {"dev", "local"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EntityManager entityManager;

    private Orders orders;

//    @BeforeEach
//    void setUp() {
//        Member member = Mockito.mock(Member.class);
//        memberRepository.save(member);
//        orders = buildOrders(member);
//        entityManager.flush();
//        entityManager.clear();
//
//    }

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
        entityManager.flush();
        entityManager.clear();
        Long cursor = orderRepository.findMaxOrderId() + 1;

        // findAllByMember 메서드 호출
        Slice<Orders> result = orderRepository.findByIdLessThanAndMemberOrderByIdDesc(cursor, member, pageable);

        // Slice<Orders> 객체에서 주문들의 PK 가져오기
        List<Long> sliceOrderIds = result.getContent().stream()
                .map(Orders::getId)
                .collect(Collectors.toList());

        // 비교
        assertThat(sliceOrderIds).contains(orders1.getId(), orders2.getId());
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