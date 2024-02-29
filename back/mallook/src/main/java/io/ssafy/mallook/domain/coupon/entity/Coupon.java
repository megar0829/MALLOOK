package io.ssafy.mallook.domain.coupon.entity;

import io.ssafy.mallook.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@Table(name = "coupon")
@AllArgsConstructor
@NoArgsConstructor
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private CouponType type;

    private String amount;

    @Column(name = "expired_time")
    private LocalDateTime expiredTime;
}
