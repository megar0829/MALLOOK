package io.ssafy.mallook.domain.order.dao;

import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByMemberIdAndStatusTrue(Member member, Pageable pageable);

    Optional<Order> findByIdAndStatusTrue(Long id);

    @Modifying
    @Query("update Order o set o.status = false where o.id in :deleteList and o.status = true ")
    void deleteOrder(@Param("deleteList") List<Long> deleteList);
}
