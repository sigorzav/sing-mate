package com.sigorzav.singmate.data.remote

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.sigorzav.singmate.config.EnvConfig
import com.sigorzav.singmate.data.remote.common.CommonAPI
import com.sigorzav.singmate.data.remote.interceptor.AuthInterceptor
import com.sigorzav.singmate.data.remote.user.UserAPI
import com.sigorzav.singmate.data.remote.song.SongAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

    // ✅ Retrofit Instance
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // ✅ Retrofit > UserAPI Interface
    @Provides
    @Singleton
    fun provideUserAPI(retrofit: Retrofit): UserAPI {
        return retrofit.create(UserAPI::class.java)
    }

    // ✅ Retrofit > SongAPI Interface
    @Provides
    @Singleton
    fun provideSongAPI(retrofit: Retrofit): SongAPI {
        return retrofit.create(SongAPI::class.java)
    }

    // ✅ Retrofit > CommonAPI Interface
    @Provides
    @Singleton
    fun provideCommonAPI(retrofit: Retrofit): CommonAPI {
        return retrofit.create(CommonAPI::class.java)
    }

}