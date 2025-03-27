package com.sigorzav.singmate.ui.song

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.sigorzav.singmate.viewmodel.song.SongDetailViewModel

@Composable
fun SongDetailScreen(viewModel: SongDetailViewModel = hiltViewModel()) {
    val song by viewModel.selectedSong.collectAsState()

    song?.let {
        Text(text = it.songTitle) // ✅ 선택된 곡 정보 사용
    } ?: Text("곡 정보가 없습니다")

}