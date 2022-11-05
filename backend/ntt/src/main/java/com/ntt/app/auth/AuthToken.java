package com.ntt.app.auth;

import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * packageName    : com.ntt.app.auth
 * fileName       : AuthToken
 * author         : Kim
 * date           : 2022-11-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-06        Kim       최초 생성
 */
@RequiredArgsConstructor
public class AuthToken {

    @Getter
    private final String token;
    private final String secret;

    public AuthToken(String id, Date expiry, String secret) {
        this.token = Jwts.builder()
                .setSubject(id)
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(expiry)
                .compact();
        this.secret = secret;
    }

    public boolean validate() {
        try {
            getTokenClaims();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Claims getTokenClaims() {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (SecurityException | MalformedJwtException e) {
            throw new JwtException("Jwt 토큰 구조가 올바르지 않습니다.");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("지원하지 않는 Token 입니다.");
        } catch (IllegalArgumentException e) {
            throw new JwtException("적합하지 않은 토큰 형식입니다.");
        } catch (ExpiredJwtException e) {
            throw new JwtException("토큰의 기한이 만료됐습니다.");
        } catch (SignatureException e) {
            throw new JwtException("사용자 인증에 실패했습니다.");
        } catch (Exception e) {
            throw new JwtException(e.getMessage());
        }
    }
}