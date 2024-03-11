package io.ssafy.mallook.domain.shoppingmall.entity;

import io.ssafy.mallook.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopping_mall")
public class ShoppingMall extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2_083)
    private String url;
}
