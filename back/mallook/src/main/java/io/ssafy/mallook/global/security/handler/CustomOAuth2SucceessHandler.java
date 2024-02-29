package io.ssafy.mallook.global.security.handler;

import io.ssafy.mallook.global.security.service.JwtService;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomOAuth2SucceessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Value("${client.redirect-url.success}")
    private String REDIRECT_URI_SUCCESS;

    @Value("${client.redirect-url.anonymous}")
    private String REDIRECT_URI_ANONYMOUS;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        UserSecurityDTO userSecurityDTO = (UserSecurityDTO) authentication.getPrincipal();

        String accessToken = jwtService.createAccessToken(userSecurityDTO);
        String refreshToken = jwtService.createRefreshToken(userSecurityDTO);

        String redirectURI = UriComponentsBuilder.fromUriString(REDIRECT_URI_SUCCESS)
                .queryParam("access-token", accessToken)
                .queryParam("refresh-token", refreshToken)
                .toUriString();
        response.sendRedirect(redirectURI);
    }
}