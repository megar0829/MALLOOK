package io.ssafy.mallook.domain.shoppingmall.entity;

import io.ssafy.mallook.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="shopping_mall")
public class Shoppingmall extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Size(max = 800)
    String url;
}
