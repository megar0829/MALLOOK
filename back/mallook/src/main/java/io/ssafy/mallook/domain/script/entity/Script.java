package io.ssafy.mallook.domain.script.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name = "script")
@AllArgsConstructor
@NoArgsConstructor
public class Script extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String name;

    @Column(name = "heart_count")
    private Integer heartCount;
}
