package com.sigorzav.singmate.viewmodel.user

import androidx.lifecycle.ViewModel
import com.sigorzav.singmate.data.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val songRepository: SongRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<AuthResult?>(null)
    val loginState: StateFlow<AuthResult?> = _loginState

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

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
