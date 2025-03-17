package com.sigorzav.singmate.data.repository

import android.util.Log
import com.sigorzav.singmate.data.remote.RetrofitInstance
import com.sigorzav.singmate.model.Song
import com.sigorzav.singmate.util.HttpStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SongRepository {

    suspend fun searchSongs(query: String): Flow<List<Song>> = flow {
        try {
            val response = RetrofitInstance.api.searchSongs(query)
            if (response.statusCode == HttpStatus.OK.code) {
                emit(response.data)
            } else {
                Log.e("SongRepository", "Error: ${response.statusCode}, ${response.statusName}")
                emit(emptyList())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("SongRepository", "Error: ${HttpStatus.INTERNAL_SERVER_ERROR.code}, ${HttpStatus.INTERNAL_SERVER_ERROR.message}")
            emit(emptyList())
        }
    }
}