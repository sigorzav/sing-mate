package com.sigorzav.singmate.data.repository

import com.sigorzav.singmate.data.remote.user.UserAPI
import com.sigorzav.singmate.model.request.CheckDuplicateRequest
import com.sigorzav.singmate.model.request.SignUpRequest
import com.sigorzav.singmate.util.HttpStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val userApi : UserAPI) {

    /**
     * 중복 체크
     * ex. 이메일, 닉네일 ..
     */
    fun fetchCheckDuplicate(request: CheckDuplicateRequest): Flow<Boolean> = flow {
        val response = userApi.checkDuplicate(request)
        if (response.statusCode == HttpStatus.OK.code) {
            emit(response.data)
        } else {
            throw Exception("UserRepository fetchCheckDuplicate Error: ${HttpStatus.INTERNAL_SERVER_ERROR.code}, ${HttpStatus.INTERNAL_SERVER_ERROR.message}")
        }
    }

    /**
     * 회원 가입
     */
    fun fetchSignUp(request: SignUpRequest): Flow<Boolean> = flow {
        val response = userApi.signUp(request)
        if (response.statusCode == HttpStatus.OK.code) {
            emit(response.data)
        } else {
            throw Exception("UserRepository fetchSignUp Error: ${HttpStatus.INTERNAL_SERVER_ERROR.code}, ${HttpStatus.INTERNAL_SERVER_ERROR.message}")
        }
    }
}