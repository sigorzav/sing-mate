package com.sigorzav.singmate.common.controller;

import com.sigorzav.singmate.api.response.ApiResponse;
import com.sigorzav.singmate.common.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/common/")
@Tag(name = "CommonControllerV1", description="공통 기능 API - V1")
public class CommonControllerV1 {

    private final CommonService commonService;

    @GetMapping("common-code")
    @ResponseBody
    @Operation(summary = "공통코드 조회", description = "구분과 그룹에 맞는 코드를 조회합니다.", tags = {"공통 관리"})
    public ApiResponse<Object> getCommonCode(
        @RequestParam String division,
        @RequestParam String codeGroup
    ) {
        return commonService.getCommonCode(division, codeGroup);
    }
}
