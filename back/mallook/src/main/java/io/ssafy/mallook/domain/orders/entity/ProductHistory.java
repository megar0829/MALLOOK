package io.ssafy.mallook.domain.orders.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.shoppingmall.entity.ShoppingMall;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_history")
public class ProductHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orderId;

    @NotNull
    private Integer productCount;

    @NotNull
    private Integer productPrice;

    @NotNull
    private String productName;

    private String productImage;

    @NotBlank
    private String productSize;

    @NotBlank
    private String productColor;

    @NotNull
    private Integer productFee;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "shoppingmall_id")
    private ShoppingMall shoppingmallId;
}
