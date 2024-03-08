package io.ssafy.mallook.global.util;

import io.jsonwebtoken.Claims;
import io.ssafy.mallook.domain.member.entity.MemberRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class JwtClaimsParser {
    public static Collection<GrantedAuthority> getMemberAuthorities(Claims claims) {
        Object stringAuthorities = claims.get("roles");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (stringAuthorities instanceof Collection<?>) {
            for (Object grantedAuthority : (Collection<?>) stringAuthorities) {
                if (grantedAuthority instanceof String) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + grantedAuthority));
                }
            }
        }
        return authorities;
    }
    // 익명 사용자에 대하여 익명 유저 권한 부여
    public static Collection<GrantedAuthority> getAnonymousRole() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + MemberRole.ANONYMOUS.name()));
        return authorities;
    }
}
