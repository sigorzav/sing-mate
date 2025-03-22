package com.sigorzav.singmate.viewmodel.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigorzav.singmate.data.repository.CommonRepository
import com.sigorzav.singmate.data.repository.UserRepository
import com.sigorzav.singmate.model.CommonCode
import com.sigorzav.singmate.model.enums.CommonCodeEnum
import com.sigorzav.singmate.model.request.CheckDuplicateRequest
import com.sigorzav.singmate.model.request.SignUpRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    commonRepository: CommonRepository,
    private val userRepository : UserRepository
) : ViewModel() {

    // ✅  선호 장르
    val genres: StateFlow<List<CommonCode>> = commonRepository
        .fetchCommonCode(CommonCodeEnum.SONG_GENRE.name)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily, // UI 에서 구독할 때만 시작
            initialValue = emptyList()
        )

    // ✅  사용자 중복 데이터 체크
    private val _duplicateState  = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val duplicateState: StateFlow<Map<String, Boolean>> get() = _duplicateState.asStateFlow()

    // ✅ 로딩 상태
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // ✅ 회원 가입 메시지
    private val _signUpMessage = MutableStateFlow<String?>(null)
    val signUpMessage: StateFlow<String?> = _signUpMessage
    
    // ✅  사용자 중복 데이터 체크 API
    fun fetchCheckDuplicate(request: CheckDuplicateRequest) {
        viewModelScope.launch {
            userRepository.fetchCheckDuplicate(request)
                .catch { e ->
                    e.printStackTrace()
                    _duplicateState.value = emptyMap()
                }
                .collect { response ->
                    _duplicateState.value = _duplicateState.value.toMutableMap().apply {
                        this[request.type] = response
                    }
                }
        }
    }

    // ✅  회원 가입 API
    fun signUp(request: SignUpRequest) {
        viewModelScope.launch {
            _isLoading.value = true
            _signUpMessage.value = null

            val response = userRepository.fetchSignUp(request)
                .catch { e ->
                    e.printStackTrace()
                    _signUpMessage.value = "회원가입에 실패했습니다. 다시 시도해주세요."
                }
                .collect { response ->
                    _signUpMessage.value = "회원가입이 완료되었습니다."
                }

            _isLoading.value = false
        }
    }
}