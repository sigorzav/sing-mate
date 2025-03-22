package com.sigorzav.singmate.initializer;

import com.sigorzav.singmate.api.service.RedisService;
import com.sigorzav.singmate.common.cache.CommonCodeCache;
import com.sigorzav.singmate.common.dto.CommonCodeDTO;
import com.sigorzav.singmate.common.repository.CommonCodeRepository;
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

        Map<String, Map<String, CommonCodeCache>> redisData = new HashMap<>();
        for (CommonCodeDTO data : codeData) {

            // redisKey: division_code_group
            String redisKey = data.getDivision() + "_" + data.getCodeGroup();
            String code = data.getCode();

            CommonCodeCache commonCodeCache = new CommonCodeCache(
                data.getCode(),
                data.getName(),
                data.getCodeSeq()
            );
            redisData.computeIfAbsent(redisKey, k -> new HashMap<>()).put(code, commonCodeCache);
        }

        redisService.saveCommonCodesToRedis(redisData);
        log.info("Common codes successfully saved to Redis!");
    }
}
