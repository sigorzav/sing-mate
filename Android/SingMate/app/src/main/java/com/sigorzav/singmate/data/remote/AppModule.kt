package com.sigorzav.singmate.data.remote

import com.sigorzav.singmate.data.remote.common.CommonAPI
import com.sigorzav.singmate.data.repository.cache.GenreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGenreRepository(commonAPI: CommonAPI): GenreRepository {
        return GenreRepository(commonAPI)
    }
}