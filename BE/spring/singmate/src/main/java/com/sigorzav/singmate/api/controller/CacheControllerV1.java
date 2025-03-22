package com.sigorzav.singmate.api.controller;

import com.sigorzav.singmate.api.response.ApiResponse;
import com.sigorzav.singmate.api.service.RedisService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cache/")
@Tag(name = "CacheControllerV1", description="Cache Data API")
public class CacheControllerV1 {

    private final RedisService redisService;
    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("code")
    @ResponseBody
    public ApiResponse<Object> getCommonCode(@RequestParam String codeKey) {
        return redisService.getCommonCode(codeKey);
    }
}
