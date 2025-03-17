package com.sigorzav.singmate.data.remote.song

import com.sigorzav.singmate.model.ApiResponse
import com.sigorzav.singmate.model.Song
import retrofit2.http.GET
import retrofit2.http.Query

interface SongAPI {

    /**
     * 노래 검색 (목록 조회)
     */
    @GET("song/search")
    suspend fun searchSongs(@Query("query") query: String): ApiResponse<Song>
}