package io.ssafy.mallook.domain.orders.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("status = true")
@Table(name = "orders")
@EqualsAndHashCode(callSuper = true)
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    private Long totalPrice;

    @NotNull
    private Long totalFee;

    @NotNull
    private Long totalCount;

    public boolean isCreateByTargetMember(UUID memberId) {
        return this.member.getId().equals(memberId);
    }
}
