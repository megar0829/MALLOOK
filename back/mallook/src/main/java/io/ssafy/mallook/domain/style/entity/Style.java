package io.ssafy.mallook.domain.style.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.member.entity.Member;
import io.ssafy.mallook.domain.style_product.entity.StyleProduct;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="style")
@SQLRestriction("status=TRUE")
public class Style extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String name;

    private Long heartCount;

    @OneToMany(mappedBy = "style", cascade = CascadeType.ALL)
    @Builder.Default
    private List<StyleProduct> styleProductList = new ArrayList<>();
}
