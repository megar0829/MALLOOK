package io.ssafy.mallook.domain.product.entity;

import io.ssafy.mallook.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Shoppingmall shopingmall;
    @Enumerated(EnumType.STRING)
    private MainCategory mainCategory;
    @Enumerated(EnumType.STRING)
    private SubCategory subCategory;
    private String name;
    private Long price;
    private Long quantity;
    private String brandName;
    private Long size;
    private String color;
    private Long fee;
    private String image;
    private String code;
    private String url;
}
