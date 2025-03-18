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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenreSelection(selectedGenres: List<String>, onGenreSelected: (List<String>) -> Unit) {
    val genres = listOf("발라드", "댄스", "힙합", "R&B", "팝", "재즈", "락", "트로트", "클래식")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Text(
            text = "선호 장르 선택",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            genres.forEach { genre ->
                val isSelected = genre in selectedGenres

                Box(
                    modifier = Modifier
                        .background(
                            color = if (isSelected) Color(0xFF6C63FF) else Color.White,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            val updatedGenres = if (isSelected) {
                                selectedGenres - genre
                            } else {
                                selectedGenres + genre
                            }
                            onGenreSelected(updatedGenres)
                        }
                        .border(1.dp, if (isSelected) Color(0xFF6C63FF) else Color.Gray, RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = genre,
                        fontSize = 14.sp,
                        color = if (isSelected) Color.White else Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}