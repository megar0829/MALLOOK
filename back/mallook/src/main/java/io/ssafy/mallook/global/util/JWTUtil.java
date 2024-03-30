package io.ssafy.mallook.global.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.ssafy.mallook.global.security.dao.TokenRedisDao;
import io.ssafy.mallook.global.security.exception.RefreshTokenException;
import io.ssafy.mallook.global.security.user.UserSecurityDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Log4j2
@Component
public class JWTUtil {
    private final SecretKey secretKey;
    private final long accessTokenExpireTime;
    private final long refreshTokenExpireTime;
    private final String issuer;
    private final TokenRedisDao tokenRedisDao;

    public JWTUtil(
            @Value(value = "${jwt.key.salt}")
            String jwtKey,
            @Value(value = "${jwt.expire-time.access-token}")
            long accessTokenExpireTime,
            @Value(value = "${jwt.expire-time.refresh-token}")
            long refreshTokenExpireTime,
            @Value(value = "${jwt.issuer}")
            String issuer,
            TokenRedisDao tokenRedisDao
    ) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtKey));
        this.accessTokenExpireTime = accessTokenExpireTime;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
        this.issuer = issuer;
        this.tokenRedisDao = tokenRedisDao;
    }

    public String generateAccessToken(UserSecurityDTO userSecurityDTO) {
        return generateToken(userSecurityDTO, accessTokenExpireTime);
    }

    public String generateRefreshToken(UserSecurityDTO userSecurityDTO) {
        var refreshToken = generateToken(userSecurityDTO, refreshTokenExpireTime);

        // refresh token 발급시 redis에 저장
        tokenRedisDao.save(userSecurityDTO.getId(), refreshToken, refreshTokenExpireTime);
        log.info("==============refresh token status==============");
        log.info(userSecurityDTO.getId() + tokenRedisDao.get(userSecurityDTO.getId()));
        return refreshToken;
    }

    private String generateToken(UserSecurityDTO userSecurityDTO, long expireTime) {
        Date expirationDate = new Date(new Date().getTime() + expireTime * 1000);

        return Jwts.builder()
                .signWith(secretKey, Jwts.SIG.HS256)
                .issuer(issuer)
                .expiration(expirationDate)
                .subject(userSecurityDTO.getUsername())
                .claim("roles", userSecurityDTO.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .compact();
    }

    public boolean isMatching(UUID id, String refreshToken) throws RefreshTokenException {
        return tokenRedisDao.isMatching(id, refreshToken);
    }

    public Claims verifyJwtToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
