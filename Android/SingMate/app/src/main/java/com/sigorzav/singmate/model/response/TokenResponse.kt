package com.sigorzav.singmate.model.response

import java.util.Date

/**
 * 로그인 응답 (토큰)
 */
data class TokenResponse(
    val accessToken: String,
    //val refreshToken: String
    val expiredAt: Date
)
