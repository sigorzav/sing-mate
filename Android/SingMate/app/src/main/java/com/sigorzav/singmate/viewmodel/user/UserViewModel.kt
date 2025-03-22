package com.sigorzav.singmate.viewmodel.user

import androidx.lifecycle.ViewModel
import com.sigorzav.singmate.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<AuthResult?>(null)
    val loginState: StateFlow<AuthResult?> = _loginState

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

//    // ✅  선호 장르 (캐싱)
//    val genres: StateFlow<List<CommonCode>> = genreRepository.cachedGenres
//
//    // ✅  사용자 중복 데이터 체크
//    private val _duplicateState  = MutableStateFlow<Map<String, Boolean>>(emptyMap())
//    val duplicateState: StateFlow<Map<String, Boolean>> get() = _duplicateState.asStateFlow()
//
//    // ✅  사용자 중복 데이터 체크 API
//    fun fetchCheckDuplicate(request: CheckDuplicateRequest) {
//        viewModelScope.launch {
//            userRepository.fetchCheckDuplicate(request)
//                .catch { e ->
//                    e.printStackTrace()
//                    _duplicateState.value = emptyMap()
//                }
//                .collect { response ->
//                    _duplicateState.value = _duplicateState.value.toMutableMap().apply {
//                        this[request.type] = response
//                    }
//                }
//        }
//    }

    // ✅ 로딩 상태
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // ✅ 회원 가입 메시지
    private val _signUpMessage = MutableStateFlow<String?>(null)
    val signUpMessage: StateFlow<String?> = _signUpMessage

    fun signUp(email: String, password: String) {
        // TODO: Implement custom sign-up logic
        _loginState.value = AuthResult(success = true)
    }

    fun signIn(email: String, password: String) {
        // TODO: Implement custom authentication logic
        _loginState.value = AuthResult(success = true)
        _isUserLoggedIn.value = true
    }



    fun signOut() {
        _isUserLoggedIn.value = false
    }

    fun getCurrentUserEmail(): String {
        return if (_isUserLoggedIn.value) "UserEmailPlaceholder" else "Unknown"
    }

}

data class AuthResult(val success: Boolean, val message: String? = null)
