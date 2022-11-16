package com.ntt.app.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * packageName    : com.ntt.app.cache
 * fileName       : RedisConfig
 * author         : Kim
 * date           : 2022-11-08
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private String redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setPort(Integer.parseInt(redisPort));
        redisStandaloneConfiguration.setPassword(redisPassword);

        /*
        * Jedis vs Lettuce
        *
        * 성능 테스트: https://jojoldu.tistory.com/418
        *
        * Lettuce는 Netty (비동기 이벤트 기반 고성능 네트워크 프레임워크) 기반의 Redis 클라이언트이다.
        * 비동기로 요청을 처리하기 때문에 고성능을 자랑한다.
        *
        * lettuce는 레디스 서버와 단일 커넥션으로 멀티 스레드 요청에 대해 처리가 가능하다. 내부적으로 논-블로킹 + 비동기로 구현되어 있으며 스레드 세이프하다.
        * 레디스 서버가 싱글 스레드 기반이기 때문에 어차피 다중 커넥션을 이용하더라도 성능상 이점이 있는 것도 아니다.
        * 따라서 lettuce를 이용한다면 굳이 커넥션 풀을 만들지 않고 단일 커넥션을 공유하는 것이 좋다.
        *
        * 그런데 커넥션을 공유하면 안되는 경우가 있다. 블로킹 API 사용이나 레디스 트랜잭션(multi/exec)의 경우이다.
        * 다행히 트랜잭션의 경우 스프링부트에서 알아서 전용 커넥션을 흭득한다.그런데 커넥션 풀을 사용하지 않는다면, 매번 커넥션을 생성한다.
        * ...https://jronin.tistory.com/126
        *
        * 따라서 트랜잭션을 쓰는 경우 커넥션 풀 사용을 고려하는 것이 좋다.
        * 스프링에서 직접적으로 lettuce api를 쓸 일은 없기 때문에 해당 내용은 pass
        * */

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
