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
@Table(name="style_product")
public class StyleProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
            @JoinColumn(name = "style_id")
    Style styleId;

    @ManyToOne
            @JoinColumn(name = "product_id")
    Product productId;
}
