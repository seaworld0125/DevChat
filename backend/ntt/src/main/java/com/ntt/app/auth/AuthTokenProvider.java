package com.ntt.app.auth;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * packageName    : com.ntt.app.auth
 * fileName       : AuthTokenPorvider
 * author         : Kim
 * date           : 2022-10-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-27        Kim       최초 생성
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthTokenProvider {

    private final AuthProperties properties;
    private static final String AUTHORITIES_KEY = "ROLE_";

    private Date getExpiryDate() {
        return new Date(System.currentTimeMillis() + properties.getTokenExpirationMsec());
    }

    public AuthToken createToken(String id) {
        return new AuthToken(id, getExpiryDate(), properties.getTokenSecret());
    }

    public AuthToken convertToken(String token) {
        return new AuthToken(token, properties.getTokenSecret());
    }

    public Authentication getAuthentication(AuthToken authToken) {
        Claims claims = authToken.getTokenClaims();
        CustomUserDetails principal = CustomUserDetails.create(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(principal, authToken, principal.getAuthorities());
    }
}