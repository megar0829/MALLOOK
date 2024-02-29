package io.ssafy.mallook.domain.style.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="style")
public class Style extends BaseEntity {
    @Id
    private Long id;

    //    private Member memberId;

    private String name;

    private Long heartCount;
}
