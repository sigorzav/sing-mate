package com.sigorzav.singmate.data.remote.common

import com.sigorzav.singmate.model.CommonCode
import com.sigorzav.singmate.model.response.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CommonAPI {

    /**
     * 공통 코드 조회
     */
    @GET("api/v1/cache/code")
    suspend fun searchCode(@Query("codeKey") codeKey: String): ApiResponse<List<CommonCode>>
}