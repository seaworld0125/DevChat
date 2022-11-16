package com.ntt.app.translate;

<<<<<<< Updated upstream
=======
import com.ntt.app.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * packageName    : com.ntt.app.translate
 * fileName       : TranslateController
 * author         : Kim
 * date           : 2022-10-24
 */
@Slf4j
@RestController
@RequestMapping("/api/translate")
@RequiredArgsConstructor
public class TranslateController {

<<<<<<< Updated upstream

=======
    private final RedisTemplate<String, String> redisTemplate;

    @GetMapping("/list")
    public Mono<NotionSearchDto> test(@AuthenticationPrincipal CustomUserDetails userDetails) {

        ValueOperations<String, String> vOps = redisTemplate.opsForValue();
        String accessToken = vOps.get(userDetails.getRedisNotionKey());

        return WebClient.create("https://api.notion.com/v1/search")
                .post()
                .header("Authorization", "Bearer " + accessToken)
                .header("Notion-Version", "2022-06-28")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, error -> Mono.error(RuntimeException::new))
                .bodyToMono(NotionSearchDto.class);
    }
>>>>>>> Stashed changes
}
