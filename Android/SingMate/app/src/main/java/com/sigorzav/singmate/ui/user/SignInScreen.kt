package com.sigorzav.singmate.ui.user

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sigorzav.singmate.SongActivity
import com.sigorzav.singmate.model.request.SignInRequest
import com.sigorzav.singmate.viewmodel.user.SignInViewModel

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    onSignUpClick: () -> Unit
) {
    //  ✅ 로그인 성공 > HOME 화면 이동
    val isLoggedIn by viewModel.isUserLoggedIn.collectAsState()
    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            val intent = Intent(context, SongActivity::class.java)
            context.startActivity(intent)
            
            // 로그인 화면 종료
            if (context is Activity) {
                context.finish()
            }
        }
    }

    // ❌ 로그인 실패 > 메세지 표시
    val errorMessage by viewModel.loginErrorMessage.collectAsState()
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearLoginError() // 아래 함수 참조
        }
    }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "로그인", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("이메일") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("비밀번호") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val signInRequest = SignInRequest(
                    email = email,
                    password = password
                )
                viewModel.fetchSignIn(signInRequest)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("로그인")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onSignUpClick) {
            Text("회원가입")
        }
    }
}
