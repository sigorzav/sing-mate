package com.sigorzav.singmate.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MusicSearchAPI {

    @GET("song/search-list")
    suspend fun searchMusic(@Query("query") query: String): List<String>
}