import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.sigorzav.singmate.viewmodel.user.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(viewModel: UserViewModel, onSignInClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var emailChecked by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var birthDay by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "회원가입", style = MaterialTheme.typography.headlineMedium)

        // EMAIL
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailChecked = false
                emailError = null
            },
            label = { Text("이메일") },
            modifier = Modifier.weight(1f),
            isError = emailError != null
        )

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            coroutineScope.launch {
                //emailError = viewModel.checkEmailDuplicate(email)
                emailChecked = emailError == null
            }
        }) {
            Text("중복 확인")
        }

        Spacer(modifier = Modifier.height(8.dp))

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
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

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
