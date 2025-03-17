package com.sigorzav.singmate.viewmodel.song

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigorzav.singmate.data.remote.RetrofitInstance
import com.sigorzav.singmate.model.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

class MusicSearchViewModel : ViewModel() {

    private val _searchResults = MutableStateFlow<List<Song>>(emptyList())
    val searchResults: StateFlow<List<Song>> = _searchResults

    private val client = OkHttpClient()

    fun searchMusic(query: String) {
        viewModelScope.launch {
            try {
                var response = RetrofitInstance.api.searchMusic(query)
                if (response.statusCode == 200) {
                    _searchResults.emit(response.data)
                } else {
                    _searchResults.emit(emptyList())
                }

            } catch (e: Exception) {
                e.printStackTrace()
                _searchResults.value = emptyList()
            }
        }
    }
}
