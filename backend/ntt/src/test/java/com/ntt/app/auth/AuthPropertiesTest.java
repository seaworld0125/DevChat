package com.ntt.app.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : com.ntt.app.auth
 * fileName       : AppPropertiesTest
 * author         : Kim
 * date           : 2022-10-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-26        Kim       최초 생성
 */
@SpringBootTest
class AuthPropertiesTest {

    @Autowired
    private AuthProperties properties;

    @Test
    void 프로퍼티_주입_확인_테스트() {

        assertThat(properties.getTokenSecret()).isEqualTo("myTestJwtTokenSecretYaYaYaYaYaYaYaYa");
        assertThat(properties.getTokenExpirationMsec()).isEqualTo(21600000);
    }
}