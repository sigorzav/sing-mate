package com.sigorzav.singmate.data.remote.user

import com.sigorzav.singmate.model.request.CheckDuplicateRequest
import com.sigorzav.singmate.model.request.SignInRequest
import com.sigorzav.singmate.model.request.SignUpRequest
import com.sigorzav.singmate.model.response.ApiResponse
import com.sigorzav.singmate.model.response.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    /**
     * 중복 체크
     * ex. 이메일, 닉네일 ..
     */
    @POST("api/v1/user/check-duplicate")
    suspend fun checkDuplicate(@Body request: CheckDuplicateRequest): ApiResponse<Boolean>

    /**
     * 회원 가입
     */
    @POST("api/v1/user/sign-up")
    suspend fun signUp(@Body request: SignUpRequest): ApiResponse<Boolean>

    /**
     * 로그인
     */
    @POST("api/v1/user/sign-in")
    suspend fun signIn(@Body request: SignInRequest): ApiResponse<TokenResponse>
}