package com.sigorzav.singmate.viewmodel.song

import androidx.lifecycle.ViewModel
import com.sigorzav.singmate.data.repository.SongRepository
import com.sigorzav.singmate.model.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SongDetailViewModel @Inject constructor(private val songRepository: SongRepository) : ViewModel() {

    private val _selectedSong = MutableStateFlow<Song?>(null)
    val selectedSong: StateFlow<Song?> = _selectedSong

    fun selectSong(song: Song) {
        _selectedSong.value = song
    }

}