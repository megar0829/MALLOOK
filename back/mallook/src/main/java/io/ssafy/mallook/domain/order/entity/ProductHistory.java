package io.ssafy.mallook.domain.order.entity;

import io.ssafy.mallook.domain.shoppingmall.entity.Shoppingmall;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class ProductHistory {
    @Id
    private Long id;
//    private Order orderId;
    private Long productCount;
    private Long productPrice;
    private String productName;
    private String productImage;
    private String productSize;
    private String productColor;
    private Long productFee;
//    private Shoppingmall shoppingmallId;
}
