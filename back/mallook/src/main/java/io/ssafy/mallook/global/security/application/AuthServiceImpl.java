package io.ssafy.mallook.global.security.application;

import io.ssafy.mallook.domain.member.dao.MemberRepository;
import io.ssafy.mallook.global.common.code.ErrorCode;
import io.ssafy.mallook.global.exception.BaseExceptionHandler;
import io.ssafy.mallook.global.security.dto.KakaoAuthTokenRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final static String KAKAO_AUTH_URL = "https://kapi.kakao.com/v2/user/me";
    private final MemberRepository memberRepository;

    @Override
    public KakaoAuthTokenRes verifyKakaoToken(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<KakaoAuthTokenRes> response = restTemplate.postForEntity(KAKAO_AUTH_URL, entity, KakaoAuthTokenRes.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            // 사용자 검색 로직
            return response.getBody();
        }

        throw new BaseExceptionHandler(ErrorCode.NO_SOCIAL_USER_ATTRIBUTES);
    }
}
