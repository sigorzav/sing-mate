package com.sigorzav.singmate.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 로그인 요청 DTO
  */
@Getter
@Setter
public class SignInRequestDTO {

    // 이메일
    @NotBlank(message = "Type must not be blank")
    private String email;

    // 패스워드
    @NotBlank(message = "Value must not be blank")
    private String password;

}
