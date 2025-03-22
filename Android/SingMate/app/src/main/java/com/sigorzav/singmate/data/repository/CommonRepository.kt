package com.sigorzav.singmate.data.repository

import com.sigorzav.singmate.data.remote.common.CommonAPI
import com.sigorzav.singmate.model.CommonCode
import com.sigorzav.singmate.util.HttpStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CommonRepository @Inject constructor(private val commonAPI: CommonAPI){

    /**
     * 공통 코드 조회
     */
    fun fetchCommonCode(codeKey: String): Flow<List<CommonCode>> = flow {
        val response = commonAPI.searchCode(codeKey)
        if (response.statusCode == HttpStatus.OK.code) {
            emit(response.data)
        } else {
            throw Exception("CommonRepository Error: ${HttpStatus.INTERNAL_SERVER_ERROR.code}, ${HttpStatus.INTERNAL_SERVER_ERROR.message}")
        }
    }
}