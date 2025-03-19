package com.sigorzav.singmate.ui.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sigorzav.singmate.model.request.CheckDuplicateRequest
import com.sigorzav.singmate.ui.component.BirthDateField
import com.sigorzav.singmate.ui.component.GenderSelection
import com.sigorzav.singmate.ui.component.GenreSelection
import com.sigorzav.singmate.viewmodel.user.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(viewModel: UserViewModel = viewModel(), onSignInClick: () -> Unit = {}) {
    val duplicateState by viewModel.duplicateState.collectAsState()
    val genres by viewModel.genres.collectAsState()
    val isSaveEnabled = !(duplicateState["EMAIL"] == true || duplicateState["NICKNAME"] == true)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf(emptyList<String>()) }
    var birthDay by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("M") }

    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "회원가입", style = MaterialTheme.typography.headlineMedium)
        }

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
        item {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("이메일") },
                modifier = Modifier.fillMaxWidth().then(emailFocusModifier)
            )
        }

        item {
            Spacer(modifier = Modifier.height(2.dp))
        }

        item {
            if (duplicateState["EMAIL"] == true) {
                Text("사용할 수 없는 이메일입니다. 다른 이메일을 입력해주세요.", color = MaterialTheme.colorScheme.error)
            }
        }

        item {
            Spacer(modifier = Modifier.height(6.dp))
        }

        // PASSWORD
        item {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("비밀번호") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        // CONFIRM_PASSWORD
        item {
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("비밀번호 확인") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            Spacer(modifier = Modifier.height(2.dp))
        }

        item {
            if (password != confirmPassword) {
                Text("동일한 비밀번호를 입력해주세요.", color = MaterialTheme.colorScheme.error)
            }
        }

        item {
            Spacer(modifier = Modifier.height(6.dp))
        }

        // NICKNAME
        item {
            OutlinedTextField(
                value = nickname,
                onValueChange = { nickname = it },
                label = { Text("닉네임") },
                modifier = Modifier.fillMaxWidth().then(nicknameFocusModifier)
            )
        }

        item {
            Spacer(modifier = Modifier.height(2.dp))
        }

        item {
            if (duplicateState["NICKNAME"] == true) {
                Text("사용할 수 없는 닉네임입니다. 다른 닉네임을 입력해주세요.", color = MaterialTheme.colorScheme.error)
            }
        }

        item {
            Spacer(modifier = Modifier.height(6.dp))
        }

        // BIRTHDAY
        item {
            BirthDateField(
                birthDay = birthDay,
                onValueChange = { birthDay = it }
            )
        }

        // GENRE
        item {
            GenreSelection(
                selectedGenres = genre,
                onGenreSelected = { genre = it },
                genres = genres
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // GENDER
        item {
            GenderSelection(
                selectedGender = gender,
                onGenderSelected = { gender = it }
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
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
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            TextButton(onClick = onSignInClick) {
                Text("로그인 화면으로 이동")
            }
        }
    }
}
