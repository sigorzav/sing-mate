package com.sigorzav.singmate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 공통코드
     */
    public void saveCommonCodesToRedis(Map<String, Map<String, String>> data) {
        data.forEach((key, value) -> {
            redisTemplate.opsForHash().putAll(key, value);
        });
    }
}
