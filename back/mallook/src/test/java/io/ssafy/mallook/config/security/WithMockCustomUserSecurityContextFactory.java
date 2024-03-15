package io.ssafy.mallook.config.security;

import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser annotation) {
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        UserSecurityDTO userSecurityDTO = UserSecurityDTO.fromSocial()
                .username("123e4567-e89b-12d3-a456-426614174000")
                .password("password")
                .authorities(authorities)
                .props(new HashMap<>())
                .create();
        Authentication auth = new UsernamePasswordAuthenticationToken(userSecurityDTO, null, authorities);
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(auth);
        return securityContext;
    }
}
