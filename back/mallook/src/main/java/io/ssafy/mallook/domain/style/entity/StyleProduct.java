package io.ssafy.mallook.domain.style.entity;

import io.ssafy.mallook.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class StyleProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
//    Style styleId;
//    Product productId;
}
