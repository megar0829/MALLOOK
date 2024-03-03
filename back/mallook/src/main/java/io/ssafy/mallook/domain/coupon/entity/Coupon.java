package io.ssafy.mallook.domain.coupon.entity;

import io.ssafy.mallook.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
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
    public Coupon(Long id) {
        this.id = id;
    }
}
