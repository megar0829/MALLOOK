package io.ssafy.mallook.domain.cart.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "total_fee")
    private Integer totalFee;

    @Column(name = "total_count")
    private Integer totalCount;
}
