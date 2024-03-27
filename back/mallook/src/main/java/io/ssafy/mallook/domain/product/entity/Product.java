package io.ssafy.mallook.domain.product.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.shoppingmall.entity.ShoppingMall;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @NotNull
    @ManyToOne
    @JoinColumn(name="shoppingmall_id")
    private ShoppingMall shopingmall;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MainCategory mainCategory;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SubCategory subCategory;

    @NotBlank
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer quantity;

    private String brandName;

    @NotBlank
    private String size;

    @NotBlank
    private String color;

    @NotNull
    private Integer fee;

    private String image;

    private String code;

    private String url;

    @ElementCollection
    private List<String> keywordList;
}
