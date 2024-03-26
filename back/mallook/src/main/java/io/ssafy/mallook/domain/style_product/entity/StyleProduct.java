package io.ssafy.mallook.domain.style_product.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.style.entity.Style;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="style_product")
@SQLRestriction("status= TRUE")
public class StyleProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "style_id")
    Style style;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;
}
