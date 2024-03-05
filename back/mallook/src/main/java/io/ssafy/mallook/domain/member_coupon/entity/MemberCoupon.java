package io.ssafy.mallook.domain.member_coupon.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.coupon.entity.Coupon;
import io.ssafy.mallook.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member_coupon")
public class MemberCoupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    Member member;
    @ManyToOne
    @JoinColumn(name="coupon_id")
    Coupon coupon;
}
