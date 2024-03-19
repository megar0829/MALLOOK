package io.ssafy.mallook.domain.keyword.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.product.entity.Product;
import io.ssafy.mallook.domain.script.entity.Script;
import io.ssafy.mallook.domain.style.entity.Style;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "keyword")
public class Keyword extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "script_id")
    private Script script;

    @NotNull
    private String name;

    //상품 아이디
    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    //스타일 아이디
    @ManyToOne
    @JoinColumn(name = "style_id")
    private Style style;
}
