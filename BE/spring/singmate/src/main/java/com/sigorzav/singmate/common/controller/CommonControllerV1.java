package com.sigorzav.singmate.common.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/common/")
@Tag(name = "CommonControllerV1", description="공통 기능 API - V1")
public class CommonControllerV1 {

}
