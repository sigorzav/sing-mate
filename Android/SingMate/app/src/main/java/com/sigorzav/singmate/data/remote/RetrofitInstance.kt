package com.sigorzav.singmate.data.remote

import com.sigorzav.singmate.config.EnvConfig
import com.sigorzav.singmate.data.remote.song.SongAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val BASE_URL = EnvConfig.BaseUrl.valueOf(EnvConfig.ENVIRONMENT).url

    /**
     * 노래 검색 API
     */
    val api: SongAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SongAPI::class.java)
    }
}