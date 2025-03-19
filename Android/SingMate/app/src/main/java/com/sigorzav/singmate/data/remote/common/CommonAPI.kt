package com.sigorzav.singmate.data.remote.common

import com.sigorzav.singmate.model.CommonCode
import com.sigorzav.singmate.model.response.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CommonAPI {

    /**
     * 공통코드 조회
     */
    @GET("api/v1/common/common-code")
    suspend fun searchCode(
        @Query("division") division: String,
        @Query("codeGroup") codeGroup: String
    ): ApiResponse<List<CommonCode>>
}