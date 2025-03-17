package com.sigorzav.singmate.model

data class ApiResponse<T>(
    val statusCode: Int,        // 상태 코드
    val statusName: String,     // 상태 코드 이름
    val message: String,        // 응답 메세지
    val data:List<T>            // 응답 데이터
)