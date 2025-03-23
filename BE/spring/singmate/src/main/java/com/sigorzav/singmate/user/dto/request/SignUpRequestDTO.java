package com.sigorzav.singmate.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 회원가입 요청 DTO
  */
@Getter
@Setter
public class SignUpRequestDTO {

    // 이메일
    @NotBlank(message = "Type must not be blank")
    private String email;

    // 패스워드(암호화)
    @NotBlank(message = "Value must not be blank")
    private String password;

    // 닉네임
    @NotBlank(message = "Nickname must not be blank")
    private String nickname;

    // 생년월일 (8자리, yyyyMMdd)
    @NotBlank(message = "BirthDay must not be blank")
    private String birthDay;

    // 성별
    @NotBlank(message = "Gender must not be blank")
    private String gender;

    // 선호 장르
    @NotBlank(message = "Genres must not be blank")
    private List<String> genres;

}
