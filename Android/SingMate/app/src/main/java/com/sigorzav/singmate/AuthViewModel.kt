package com.sigorzav.singmate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _loginState = MutableStateFlow<AuthResult?>(null)
    val loginState: StateFlow<AuthResult?> = _loginState

    private val _isUserLoggedIn = MutableStateFlow(auth.currentUser != null)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _loginState.value = AuthResult(success = true)
                        _isUserLoggedIn.value = true
                    } else {
                        _loginState.value = AuthResult(success = false, message = task.exception?.message ?: "Login failed")
                    }
                }
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _loginState.value = AuthResult(success = true)
                    } else {
                        _loginState.value = AuthResult(success = false, message = task.exception?.message ?: "Sign up failed")
                    }
                }
        }
    }

    fun signOut() {
        auth.signOut()
        _isUserLoggedIn.value = false
    }

    fun getCurrentUserEmail(): String {
        return auth.currentUser?.email ?: "Unknown"
    }

}

data class AuthResult(val success: Boolean, val message: String? = null)
