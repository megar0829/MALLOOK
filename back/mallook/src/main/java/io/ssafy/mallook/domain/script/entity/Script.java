package io.ssafy.mallook.domain.script.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.keyword.entity.Keyword;
import io.ssafy.mallook.domain.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;
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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @NotBlank
    private String name;

    @NotNull
    @Column(name = "heart_count")
    private Integer heartCount;

    @NotNull
    @Column(name = "total_like")
    private Integer totalLike;

    @Builder.Default
    @ElementCollection
    private List<String> keywordList = new ArrayList<>();

    public boolean isWrittenByTargetMember(UUID memberId) {
        return this.member.getId().equals(memberId);
    }

    public void like() {
        this.heartCount++;
        this.totalLike++;
    }

    public void unlike() {
        this.heartCount--;
        this.totalLike--;
    }
}
