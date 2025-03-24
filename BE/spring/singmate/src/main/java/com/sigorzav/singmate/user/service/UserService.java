package com.sigorzav.singmate.user.service;

import com.sigorzav.singmate.api.response.ApiResponse;
import com.sigorzav.singmate.user.dto.request.CheckDuplicateRequestDTO;
import com.sigorzav.singmate.user.dto.request.SignInRequestDTO;
import com.sigorzav.singmate.user.dto.request.SignUpRequestDTO;

public interface UserService {

    /**
     * 사용자 중복 데이터 체크
     */
    ApiResponse<Object> checkDuplicate(CheckDuplicateRequestDTO checkDuplicateRequestDTO);

    /**
     * 회원가입
     */
    ApiResponse<Object> signUp(SignUpRequestDTO signUpRequestDTO);

    /**
     * 로그인
     */
    ApiResponse<Object> signIn(SignInRequestDTO signInRequestDTO);
}
