package com.ntt.app.auth;

import lombok.Getter;
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
@Component
@ConfigurationProperties(prefix = "app.auth")
@ConstructorBinding
@Getter
public class AuthProperties {

    private String tokenSecret;
    private long tokenExpirationMsec;
}
