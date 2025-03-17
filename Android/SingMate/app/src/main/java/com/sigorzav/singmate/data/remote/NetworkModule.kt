package com.sigorzav.singmate.data.remote

import com.sigorzav.singmate.config.EnvConfig
import com.sigorzav.singmate.data.remote.song.SongAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * ✅ @Module    :: Can be managed by hilt
 * ✅ @InstallIn :: Use Global Apps (Single-ton range)
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val BASE_URL = EnvConfig.BaseUrl.valueOf(EnvConfig.ENVIRONMENT).url

    // ✅ Retrofit Instance
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // ✅ Retrofit > SongAPI Interface
    @Provides
    @Singleton
    fun provideSongAPI(retrofit: Retrofit): SongAPI {
        return retrofit.create(SongAPI::class.java)
    }

}