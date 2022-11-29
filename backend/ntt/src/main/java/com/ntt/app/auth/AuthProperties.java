package com.ntt.app.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * packageName    : com.ntt.app.auth
 * fileName       : AppProperties
 * author         : Kim
 * date           : 2022-10-26
 */
@ConfigurationProperties(prefix = "app.auth")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
public class AuthProperties {

    private final String tokenSecret;
    private final long tokenExpirationMsec;
}
