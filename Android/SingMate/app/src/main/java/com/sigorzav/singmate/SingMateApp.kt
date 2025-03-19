package com.sigorzav.singmate

import android.app.Application
import com.facebook.stetho.Stetho
import com.sigorzav.singmate.data.repository.cache.GenreRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class SingMateApp : Application() {

    @Inject
    lateinit var genreRepository: GenreRepository

    override fun onCreate() {
        super.onCreate()

        // ✅ Cache API 호출
        CoroutineScope(Dispatchers.IO).launch {
            genreRepository.fetchGenres().collect()
        }

        // ✅ Stetho 초기화
        Stetho.initializeWithDefaults(this)
    }

    // ✅ Stetho URL - chrome://inspect/#devices
}