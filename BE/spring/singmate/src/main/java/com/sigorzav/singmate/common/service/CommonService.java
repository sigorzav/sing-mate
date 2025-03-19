package com.sigorzav.singmate.common.service;

import com.sigorzav.singmate.api.response.ApiResponse;
import com.sigorzav.singmate.common.dto.CommonCodeDTO;

import java.util.List;

public interface CommonService {

    /**
     * 공통코드 조회
     */
    ApiResponse<Object> getCommonCode(String division, String codeGroup);
}
