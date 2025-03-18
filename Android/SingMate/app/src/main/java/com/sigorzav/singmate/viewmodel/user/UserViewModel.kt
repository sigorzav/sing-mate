package com.sigorzav.singmate.viewmodel.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigorzav.singmate.data.repository.UserRepository
import com.sigorzav.singmate.model.request.CheckDuplicateRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<AuthResult?>(null)
    val loginState: StateFlow<AuthResult?> = _loginState

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    private val _duplicateState  = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val duplicateState: StateFlow<Map<String, Boolean>> get() = _duplicateState .asStateFlow()

    fun fetchCheckDuplicate(request: CheckDuplicateRequest) {
        viewModelScope.launch {
            userRepository.fetchCheckDuplicate(request)
                .catch { e ->
                    e.printStackTrace()
                    _duplicateState .value = emptyMap()
                }
                .collect { response ->
                    _duplicateState .value = _duplicateState .value.toMutableMap().apply {
                        this[request.type] = response
                    }
                }
        }
    }

    fun signIn(email: String, password: String) {
        // TODO: Implement custom authentication logic
        _loginState.value = AuthResult(success = true)
        _isUserLoggedIn.value = true
    }

    fun signUp(email: String, password: String) {
        // TODO: Implement custom sign-up logic
        _loginState.value = AuthResult(success = true)
    }

    fun signOut() {
        _isUserLoggedIn.value = false
    }

    fun getCurrentUserEmail(): String {
        return if (_isUserLoggedIn.value) "UserEmailPlaceholder" else "Unknown"
    }

}

data class AuthResult(val success: Boolean, val message: String? = null)
