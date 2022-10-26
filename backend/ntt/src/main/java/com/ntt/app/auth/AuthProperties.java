package com.ntt.app.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.ntt.app.auth
 * fileName       : AppProperties
 * author         : Kim
 * date           : 2022-10-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-26        Kim       최초 생성
 * 2022-10-26        Kim       properties 필드 설정
 * 2022-10-26        Kim       immutable 하도록 @ConstructorBinding 적용
 */
@ConfigurationProperties(prefix = "app.auth")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
public class AuthProperties {

    private final String tokenSecret;
    private final long tokenExpirationMsec;
}
