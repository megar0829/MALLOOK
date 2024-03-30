package io.ssafy.mallook.global.security.dao;

import io.ssafy.mallook.global.security.exception.RefreshTokenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Log4j2
@Repository
@RequiredArgsConstructor
public class TokenRedisDao {
    private final StringRedisTemplate redisTemplate;
    private static final String REFRESH_HASH = "refresh-token:";

    public void save(UUID id, String refreshToken, long expirationTime) {
        redisTemplate.opsForValue().set(REFRESH_HASH + id, refreshToken, expirationTime, TimeUnit.SECONDS);
    }

    public String get(UUID id) throws RefreshTokenException {
        var token = redisTemplate.opsForValue().get(REFRESH_HASH + id);
        if (Objects.isNull(token)) {
            throw new RefreshTokenException(RefreshTokenException.REFRESH_TOKEN_ERROR.NO_REFRESH);
        }
        return token;
    }

    public boolean isMatching(UUID id, String refreshToken) throws RefreshTokenException {
        var storedToken = redisTemplate.opsForValue().get(REFRESH_HASH + id);
        log.info("=============refresh-token check=============");
        if (storedToken == null) {
            log.info("stored token is null");
        } else {
            log.info(id + " " + storedToken + " " + storedToken.equals(refreshToken));
        }
        if (Objects.isNull(storedToken)) {
            throw new RefreshTokenException(RefreshTokenException.REFRESH_TOKEN_ERROR.NO_REFRESH);
        }
        return storedToken.equals(refreshToken);
    }
}
