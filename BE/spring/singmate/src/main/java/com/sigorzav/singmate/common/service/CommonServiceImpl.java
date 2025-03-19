package com.sigorzav.singmate.common.service;

import com.sigorzav.singmate.api.response.ApiResponse;
import com.sigorzav.singmate.common.dto.CommonCodeDTO;
import com.sigorzav.singmate.common.repository.CommonCodeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

    private final CommonCodeRepository commonCodeRepository;

    /**
     * 공통코드 조회
     */
    @Override
    public ApiResponse<Object> getCommonCode(String division, String codeGroup) {
        log.info("getCommonCode request processing. division: {}, codeGroup: {}", division, codeGroup);

        try {
            List<CommonCodeDTO> commonCodes = commonCodeRepository.findByDivisionAndCodeGroup(division, codeGroup);

            if (commonCodes == null || commonCodes.isEmpty()) {
                return new ApiResponse<>(HttpStatus.NO_CONTENT, "No common codes");
            }

            return new ApiResponse<>(commonCodes);
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching common codes: " + e.getMessage());
        }
    }

}
