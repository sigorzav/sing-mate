package com.sigorzav.singmate.viewmodel.song

import androidx.lifecycle.ViewModel
import com.sigorzav.singmate.data.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongHomeViewModel @Inject constructor(
    private val songRepository : SongRepository
) : ViewModel() {

}