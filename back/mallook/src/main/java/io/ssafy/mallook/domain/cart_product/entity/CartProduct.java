package io.ssafy.mallook.domain.cart_product.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.cart.entity.Cart;
import io.ssafy.mallook.domain.product.entity.Product;
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
@Table(name = "cart_product")
public class CartProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    // 상품 id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_count")
    private Integer productCount;

    @Column(name = "product_price")
    private Integer productPrice;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_image")
    private String productImage;

    @Column(name = "product_size")
    private String productSize;

    @Column(name = "product_color")
    private String productColor;

    @Column(name = "product_fee")
    private Integer productFee;

    //쇼핑몰 id
//    private Long shopMallId;
}
