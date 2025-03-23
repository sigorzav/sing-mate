package com.sigorzav.singmate.model.request

/**
 * 회원 가입 요청
 */
data class SignUpRequest(
    val email: String,          // 이메일
    val password: String,       // 비밀번호
    val nickname: String,       // 닉네임
    val birthDay: String,       // 생년월일 (8자리, yyyyMMdd)
    val gender: String,         // 성별
    val genres: List<String>    // 선호 장르
)
