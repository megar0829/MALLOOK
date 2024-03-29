package io.ssafy.mallook.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(exclude = {"id"})
public class SocialMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_member_id")
    private Long id;
    private String socialId;
    @Enumerated(EnumType.STRING)
    private SocialType socialType;
    private String email;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        member.getSocialMembers().add(this);
    }
}
