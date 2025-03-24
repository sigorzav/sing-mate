package com.sigorzav.singmate.model.request

/**
 * 로그인 요청
 */
data class SignInRequest(
    val email: String,          // 이메일
    val password: String,       // 비밀번호
)
