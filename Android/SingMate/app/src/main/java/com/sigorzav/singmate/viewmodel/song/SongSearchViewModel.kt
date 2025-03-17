package com.sigorzav.singmate.viewmodel.song

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sigorzav.singmate.data.repository.SongRepository
import com.sigorzav.singmate.model.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongSearchViewModel @Inject constructor(private val songRepository: SongRepository) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<Song>>(emptyList())
    val searchResults: StateFlow<List<Song>> = _searchResults.asStateFlow()

    fun fetchSearchSongs(query: String) {
        viewModelScope.launch {
            songRepository.fetchSearchSongs(query)
                .catch { e ->
                    e.printStackTrace()
                    _searchResults.value = emptyList()
                }
                .collect { response ->
                    _searchResults.emit(response)
                }
        }
    }
}
