package io.ssafy.mallook.global.security.filter;

import io.ssafy.mallook.global.security.exception.AccessTokenException;
import io.ssafy.mallook.global.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class JwtAuthenticateFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final String[] URL_WHITE_LIST;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        log.trace("Request URI: {}", request.getRequestURI());
        log.trace("Request Method: {}", request.getMethod());
        log.trace("Request Params: {}", request.getParameterMap());
        log.trace("Access-token: {}", request.getHeader("Authorization"));

        try {
            Authentication authentication = jwtService.authenticateAccessToken(request);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (AccessTokenException accessTokenException) {
            accessTokenException.addResponseError(response);
        }
    }
}