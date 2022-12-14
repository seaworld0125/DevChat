package com.ntt.app.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.ntt.app.auth
 * fileName       : AuthController
 * author         : Kim
 * date           : 2022-11-08
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RedisTemplate<String, String> redisTemplate;

    @GetMapping("/tistory/oauth2")
    public ResponseEntity tistoryOauth2(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam("code") String code
    ) {
        log.info("code = {}", code);

        ValueOperations<String, String> vOps = redisTemplate.opsForValue();
        vOps.set(userDetails.getRedisTistoryKey(), code);

        return ResponseEntity.ok().build();
    }
}
