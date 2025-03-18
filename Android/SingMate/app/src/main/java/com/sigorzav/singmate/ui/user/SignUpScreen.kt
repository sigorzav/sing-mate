package com.sigorzav.singmate.ui.user

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sigorzav.singmate.model.request.CheckDuplicateRequest
import com.sigorzav.singmate.viewmodel.user.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(viewModel: UserViewModel = viewModel(), onSignInClick: () -> Unit = {}) {
    val duplicateState by viewModel.duplicateState.collectAsState()
    val isSaveEnabled = !(duplicateState["EMAIL"] == true || duplicateState["NICKNAME"] == true)

    var email by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var birthDay by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "회원가입", style = MaterialTheme.typography.headlineMedium)

        // Email CheckDuplicate
        val emailFocusModifier = Modifier.onFocusChanged { focusState ->
            if (!focusState.isFocused && email.isNotBlank()) {
                coroutineScope.launch {
                    val emailRequest = CheckDuplicateRequest(type = "EMAIL", value = email)
                    viewModel.fetchCheckDuplicate(emailRequest)
                }
            }
        }

        // Nickname CheckDuplicate
        val nicknameFocusModifier = Modifier.onFocusChanged { focusState ->
            if (!focusState.isFocused && nickname.isNotBlank()) {
                coroutineScope.launch {
                    val nicknameRequest = CheckDuplicateRequest(type = "NICKNAME", value = nickname)
                    viewModel.fetchCheckDuplicate(nicknameRequest)
                }
            }
        }

        // EMAIL
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("이메일") },
            modifier = Modifier.fillMaxWidth().then(emailFocusModifier)
        )

        Spacer(modifier = Modifier.height(2.dp))

        if (duplicateState["EMAIL"] == true) {
            Text("사용할 수 없는 이메일입니다. 다른 이메일을 입력해주세요.", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(6.dp))

        // PASSWORD
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("비밀번호") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // CONFIRM_PASSWORD
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("비밀번호 확인") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // NICKNAME
        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { Text("닉네임") },
            modifier = Modifier.fillMaxWidth().then(nicknameFocusModifier)
        )

        Spacer(modifier = Modifier.height(2.dp))

        if (duplicateState["NICKNAME"] == true) {
            Text("사용할 수 없는 닉네임입니다. 다른 닉네임을 입력해주세요.", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(6.dp))

        // BIRTHDAY
        OutlinedTextField(
            value = birthDay,
            onValueChange = { birthDay = it },
            label = { Text("생년월일") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // GENDER
        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("성별") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (password == confirmPassword) {
                    viewModel.signUp(email, password)
                }
            },
            enabled = isSaveEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("회원가입")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onSignInClick) {
            Text("로그인 화면으로 이동")
        }
    }
}