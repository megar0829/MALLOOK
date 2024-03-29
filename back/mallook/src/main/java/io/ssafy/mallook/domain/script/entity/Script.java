package io.ssafy.mallook.domain.script.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Getter
@Builder
@Entity
@Table(name = "script")
@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction("status = TRUE")
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

    public boolean isWrittenByTargetMember(UUID memberId) {
        return this.member.getId().equals(memberId);
    }

    public void like() {
        this.heartCount++;
    }

    public void unlike() {
        this.heartCount--;
    }
}
