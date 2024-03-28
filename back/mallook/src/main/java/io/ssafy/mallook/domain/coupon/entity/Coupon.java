package io.ssafy.mallook.domain.coupon.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.member_coupon.entity.MemberCoupon;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "coupon")
@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction("status=TRUE")
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CouponType type;

    @NotBlank
    private String amount;

    @NotNull
    @Column(name = "expired_time")
    private LocalDateTime expiredTime;  //todo: expiredtime  되면 쿠폰 삭제하기

    private Integer stock;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberCoupon> memberCouponList = new ArrayList<>();



    public Coupon(Long id) {
        this.id = id;
    }   //todo: 수정

}
