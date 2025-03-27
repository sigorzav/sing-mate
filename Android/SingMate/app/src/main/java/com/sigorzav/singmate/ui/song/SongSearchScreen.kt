package com.sigorzav.singmate.ui.song

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sigorzav.singmate.model.Song
import com.sigorzav.singmate.viewmodel.song.SongSearchViewModel

@Composable
fun SongSearchScreen(
    viewModel: SongSearchViewModel = hiltViewModel(),
    onSongClick: (Song) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val searchResults by viewModel.searchResults.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // 검색 입력 필드
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = query,
                onValueChange = { query = it },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    viewModel.fetchSearchSongs(query)
                }),
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )

            IconButton(onClick = {
                viewModel.fetchSearchSongs(query)
            }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 검색 결과 리스트
        if (searchResults.isNotEmpty()) {
            LazyColumn {
                items(searchResults) { song ->
                    SongItem(song, onClick = onSongClick)
                }
            }
        } else {
            Text(
                text = "검색 결과가 없습니다.",
                modifier = Modifier.padding(16.dp),
                color = Color.Gray
            )
        }
    }
}

@Composable
fun SongItem(song: Song, onClick: (Song) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = song.songThumbnailUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = song.songTitle, style = MaterialTheme.typography.bodyLarge)
                Text(text = song.songArtist, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { onClick(song) },
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text("부르기")
            }
        }
    }
}