package io.ssafy.mallook.domain.style.entity;

import io.ssafy.mallook.domain.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class Style {
    @Id
    private Long id;
//    private Member memberId;
    private String name;
    private Long heartCount;
}
