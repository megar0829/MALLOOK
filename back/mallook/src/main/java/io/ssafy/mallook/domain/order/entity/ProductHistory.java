package io.ssafy.mallook.domain.order.entity;

import io.ssafy.mallook.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product_history")
public class ProductHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;
    private Long productCount;
    private Long productPrice;
    private String productName;
    private String productImage;
    private String productSize;
    private String productColor;
    private Long productFee;
//    private Shoppingmall shoppingmallId;
}
