package com.sigorzav.singmate.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import com.sigorzav.singmate.util.ValidationUtil
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun BirthDateField(birthDay: String, onValueChange: (String) -> Unit) {
    var errorMessage by remember { mutableStateOf<String?>(null) }

    OutlinedTextField(
        value = birthDay,
        onValueChange = { input ->
            val filteredInput = input.filter { it.isDigit() }.take(8) // 숫자만 입력 + 8자리 제한
            onValueChange(filteredInput)

            errorMessage = when {
                filteredInput.length != 8 -> "8자리 생년월일을 입력해주세요 (YYYYMMDD)"
                !ValidationUtil.isValidBirthDate(filteredInput) -> "유효한 날짜를 입력해주세요 (예: 19921231)"
                else -> null
            }
        },
        label = { Text("생년월일 (YYYYMMDD)") },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        isError = errorMessage != null,
        supportingText = {
            errorMessage?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}