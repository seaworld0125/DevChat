package com.ntt.app.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.startsWith;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final static String AUTHORIZATION_HEADER_PREFIX = "Authorization";
    private final static String AUTHORIZATION_TOKEN_PREFIX = "Bearer ";
    private final AuthTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER_PREFIX);

        if(isNotEmpty(authorizationHeader) && startsWith(authorizationHeader, AUTHORIZATION_TOKEN_PREFIX)) {
            String tokenStr = getAccessToken(authorizationHeader);
            AuthToken token = tokenProvider.convertToken(tokenStr);

            if(token.validate()) {
                Authentication authentication = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String header) {
        return header.substring(AUTHORIZATION_TOKEN_PREFIX.length());
    }
}
