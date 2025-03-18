package com.sigorzav.singmate.data.repository

import com.sigorzav.singmate.data.remote.song.SongAPI
import com.sigorzav.singmate.model.Song
import com.sigorzav.singmate.util.HttpStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SongRepository @Inject constructor(private val songApi: SongAPI){

    fun fetchSearchSongs(query: String): Flow<List<Song>> = flow {
        val response = songApi.searchSongs(query)
        if (response.statusCode == HttpStatus.OK.code) {
            emit(response.data)
        } else {
            throw Exception("SongRepository Error: ${HttpStatus.INTERNAL_SERVER_ERROR.code}, ${HttpStatus.INTERNAL_SERVER_ERROR.message}")
        }
    }
}