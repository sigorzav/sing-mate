package com.sigorzav.singmate.api.service;

import com.sigorzav.singmate.api.response.ApiResponse;
import com.sigorzav.singmate.api.response.CommonCodeResponse;
import com.sigorzav.singmate.common.cache.CommonCodeCache;
import com.sigorzav.singmate.common.dto.CommonCodeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        log.info("getCommonCode request processing. codeKey: {}", codeKey);

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
                return new ApiResponse<>(HttpStatus.NO_CONTENT, "No common codes");
            }

            return new ApiResponse<>(commonCodes);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching common codes: " + e.getMessage());
        }
    }
}
