package com.sigorzav.singmate.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sigorzav.singmate.model.CommonCode

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenreSelection(
    selectedGenres: List<String>,
    onGenreSelected: (List<String>) -> Unit,
    genres: List<CommonCode>
) {
    // ✅ 경고 메시지 표시 여부
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Text(
            text = "선호 장르 선택 (최대 5개)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // ✅ 경고 메시지 출력
        if (showError) {
            Text(
                text = "최대 5개까지 선택할 수 있습니다.",
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            genres.forEach { genre ->
                val isSelected = genre.code in selectedGenres

                Box(
                    modifier = Modifier
                        .background(
                            color = if (isSelected) Color(0xFF6C63FF) else Color.White,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            if (isSelected) {
                                // ✅ 선택 해제 가능
                                onGenreSelected(selectedGenres - genre.code)
                                showError = false
                            } else {
                                // ✅ 5개 초과 선택 방지
                                if (selectedGenres.size < 5) {
                                    onGenreSelected(selectedGenres + genre.code)
                                    showError = false
                                } else {
                                    showError = true // ✅ 초과 시 경고 메시지 표시
                                }
                            }
                        }
                        .border(1.dp, if (isSelected) Color(0xFF6C63FF) else Color.Gray, RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = genre.name,
                        fontSize = 14.sp,
                        color = if (isSelected) Color.White else Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}