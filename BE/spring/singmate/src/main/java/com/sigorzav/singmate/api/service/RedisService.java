package com.sigorzav.singmate.api.service;

import com.sigorzav.singmate.api.response.ApiResponse;
import com.sigorzav.singmate.api.response.CommonCodeResponse;
import com.sigorzav.singmate.common.cache.CommonCodeCache;
import com.sigorzav.singmate.common.enums.CommonCodeEnum;
import com.sigorzav.singmate.common.enums.MessageEnum;
import com.sigorzav.singmate.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * (Initializer) 공통코드
     */
    public void saveCommonCodesToRedis(Map<String, Map<String, CommonCodeCache>> data) {
        data.forEach((key, value) -> {
            redisTemplate.opsForHash().putAll(key, value);
        });
    }

    /**
     * (API) 공통코드 조회
     */
    public ApiResponse<Object> getCommonCode(String codeKey) {
        log.info("[Redis] getCommonCode request processing. codeKey: {}", codeKey);

        try {
            Map<Object, Object> redisResult = redisTemplate.opsForHash().entries(codeKey);

            List<CommonCodeResponse> commonCodes = redisResult.entrySet().stream()
                    .map(entry -> {
                        String code = (String) entry.getKey();
                        CommonCodeCache cache = (CommonCodeCache) entry.getValue();
                        return new CommonCodeResponse(code, cache.getName());
                    })
                    .toList();

            if (commonCodes.isEmpty()) {
                return ApiResponse.noContent("[Redis] No Common Codes");
            }

            return ApiResponse.success(commonCodes);
        } catch (Exception e) {
            log.error("[Redis] getCommonCode error", e);
            throw new CustomException("[Redis] getCommonCode error", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 공통 코드 조회
     */
    public Optional<CommonCodeCache> getCommonCodeFromRedis(CommonCodeEnum redisKey, String code) {
        Object value = redisTemplate.opsForHash().get(redisKey.getKey(), code);

        if (value == null) {
            log.warn("[Redis] key={}, code='{}' → Not Found", redisKey.getKey(), code);
            return Optional.empty();
        }

        try {
            return Optional.of((CommonCodeCache) value);
        } catch (ClassCastException e) {
            log.error("[Redis] Parsing Error key={}, code={}, value={}", redisKey.getKey(), code, value, e);
            return Optional.empty();
        }
    }
}
