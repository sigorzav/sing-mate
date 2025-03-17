package com.sigorzav.singmate.data.remote.song

import com.sigorzav.singmate.model.ApiResponse
import com.sigorzav.singmate.model.Song
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicSearchAPI {

    @GET("song/search")
    suspend fun searchMusic(@Query("query") query: String): ApiResponse<Song>
}