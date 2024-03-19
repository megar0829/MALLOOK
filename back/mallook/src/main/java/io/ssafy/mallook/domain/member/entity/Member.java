package io.ssafy.mallook.domain.member.entity;

import io.ssafy.mallook.domain.BaseEntity;
import io.ssafy.mallook.domain.member_coupon.entity.MemberCoupon;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLRestriction;

import java.util.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("status=TRUE")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "member_id")
    private UUID id;
    private String nickname;
    private Date birth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(unique = true)
    private String phone;
    private Long point;
    private Long exp;
    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SocialMember> socialMembers = new HashSet<>();
    @Embedded
    private Address address;
    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "member_role",
            joinColumns = @JoinColumn(name = "member_id"))
    private Set<MemberRole> role = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberCoupon> myCouponList = new ArrayList<>();
    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
    public Member(UUID id) {
        this.id = id;
    }
}


