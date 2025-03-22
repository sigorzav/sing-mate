package com.sigorzav.singmate.data.remote

import com.sigorzav.singmate.data.remote.common.CommonAPI
import com.sigorzav.singmate.data.remote.song.SongAPI
import com.sigorzav.singmate.data.remote.user.UserAPI
import com.sigorzav.singmate.data.repository.CommonRepository
import com.sigorzav.singmate.data.repository.SongRepository
import com.sigorzav.singmate.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // ✅ Common
    @Provides
    @Singleton
    fun provideCommonRepository(commonAPI: CommonAPI): CommonRepository {
        return CommonRepository(commonAPI)
    }

    // ✅ Song
    @Provides
    @Singleton
    fun provideSongRepository(songApi: SongAPI): SongRepository {
        return SongRepository(songApi)
    }

    // ✅ User
    @Provides
    @Singleton
    fun provideUserRepository(userApi : UserAPI): UserRepository {
        return UserRepository(userApi)
    }
}