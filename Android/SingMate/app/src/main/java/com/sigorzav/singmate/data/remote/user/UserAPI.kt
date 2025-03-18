package com.sigorzav.singmate.data.remote.user

import com.sigorzav.singmate.model.request.CheckDuplicateRequest
import com.sigorzav.singmate.model.response.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    /**
     * 중복 체크
     * ex. 이메일, 닉네일 ..
     */
    @POST("api/v1/user/check-duplicate")
    suspend fun checkDuplicate(@Body request: CheckDuplicateRequest): ApiResponse<Boolean>
}