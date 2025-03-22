package com.sigorzav.singmate.model.request

/**
 * 사용자 중복 데이터 체크 요청
 */
data class CheckDuplicateRequest(
    val type: String,   // 체크 필드 (email, nickname ..)
    val value: String   // 입력값
)
