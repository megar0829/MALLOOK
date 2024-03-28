package io.ssafy.mallook.domain.grade.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grade")
@SQLRestriction("status=TRUE")
public class Grade extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;


    public Grade(Level level) {
        super();
    }

    public List<Integer> getGradeRange() {
        return Level.getExpRange(this.level);
    }
}
