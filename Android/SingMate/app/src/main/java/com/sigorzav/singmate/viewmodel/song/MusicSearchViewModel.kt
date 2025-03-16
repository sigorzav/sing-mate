package com.sigorzav.singmate.viewmodel.song

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.OkHttpClient

class MusicSearchViewModel : ViewModel() {

    private val _searchResults = MutableStateFlow<List<String>>(emptyList())
    val searchResults: StateFlow<List<String>> = _searchResults

    private val client = OkHttpClient()

    fun searchMusic(query: String) {
        parseResults(
            json = TODO()
        )
    }

    private fun parseResults(json: String): List<String> {
        // JSON 파싱 로직 (간단히 가정)
        return listOf("노래 1", "노래 2", "노래 3") // 실제 JSON 파싱 필요
    }
}
