package com.sigorzav.singmate.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 사용자 중복 데이터 체크 요청 DTO
 * type: 체크 필드 (email, nickname ..)
 * value: 입력 값
 */
@Getter
@Setter
public class CheckDuplicateRequestDTO {

    @NotBlank(message = "Type must not be blank")
    private String type;

    @NotBlank(message = "Value must not be blank")
    private String value;
}
