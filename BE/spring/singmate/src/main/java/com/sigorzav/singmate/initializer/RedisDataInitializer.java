package com.sigorzav.singmate.initializer;

import com.sigorzav.singmate.common.dto.CommonCodeDTO;
import com.sigorzav.singmate.common.repository.CommonCodeRepository;
import com.sigorzav.singmate.service.RedisService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisDataInitializer {

    private final RedisService redisService;
    private final CommonCodeRepository commonCodeRepository;

    @PostConstruct
    public void loadCommonCodesIntoRedis() {
        List<CommonCodeDTO> codeData = commonCodeRepository.findBy();

        Map<String, Map<String, String>> redisData = new HashMap<>();
        for (CommonCodeDTO data : codeData) {
            String division = data.getDivision();
            String codeGroup = data.getCodeGroup();
            String code = data.getCode();
            String name = data.getName();

            // Key: division:code_group
            String key = division + ":" + codeGroup;
            redisData.computeIfAbsent(key, k -> new HashMap<>()).put(code, name);
        }

        redisService.saveCommonCodesToRedis(redisData);
        log.info("Common codes successfully saved to Redis!");
    }
}
