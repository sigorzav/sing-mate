package com.sigorzav.singmate.user.controller;

import com.sigorzav.singmate.api.response.ApiResponse;
import com.sigorzav.singmate.user.dto.request.CheckDuplicateRequestDTO;
import com.sigorzav.singmate.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/user/")
@RequiredArgsConstructor
@Tag(name = "UserControllerV1", description="사용자 관련 기능 API")
public class UserControllerV1 {

    private final UserService userService;

    @RequestMapping("check-duplicate")
    @ResponseBody
    @Operation(summary = "사용자 중복 데이터 체크", description = "이메일, 닉네임 등의 값을 중복 체크합니다.", tags = {"사용자 관리"})
    public ApiResponse<Object> checkDuplicate(@RequestBody CheckDuplicateRequestDTO checkDuplicateRequestDTO) {
        return userService.checkDuplicate(checkDuplicateRequestDTO);
    }

}
