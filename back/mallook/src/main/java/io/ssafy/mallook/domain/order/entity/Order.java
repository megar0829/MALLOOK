package io.ssafy.mallook.domain.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class Order {
    private Long id;
    private Long totalPrice;
    private Long totalFee;
    private Long totalCount;
}
