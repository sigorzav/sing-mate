package com.sigorzav.singmate.viewmodel.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigorzav.singmate.common.auth.TokenManager
import com.sigorzav.singmate.data.repository.UserRepository
import com.sigorzav.singmate.model.request.SignInRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository : UserRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    // ✅  로그인 상태
    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    // ✅  로그인 실패 메세지
    private val _loginErrorMessage = MutableStateFlow<String?>(null)
    val loginErrorMessage: StateFlow<String?> = _loginErrorMessage

    // ✅ 로그인 API
    fun fetchSignIn(request: SignInRequest) {
        viewModelScope.launch {
            userRepository.fetchSignIn(request)
                .catch { e ->
                    e.printStackTrace()
                    _loginErrorMessage.value = "로그인에 실패했습니다. 이메일 또는 비밀번호를 확인해주세요."
                }
                .collect { response ->
                    // ✅ JWT 토큰 저장
                    tokenManager.saveAccessToken(response.accessToken)
                    _isUserLoggedIn.value = true
                }
        }
    }

    // ✅ 로그인 실패 메세지 초기화
    fun clearLoginError() {
        _loginErrorMessage.value = null
    }
}