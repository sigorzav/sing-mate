package com.sigorzav.singmate

import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SingMateApp : Application() {

    // @Inject
    // lateinit var genreRepository: GenreRepository

    override fun onCreate() {
        super.onCreate()
        Log.d("SingMate App", ">>> App Start")

        // ✅ Cache API 호출 (Redis 설정 > 미사용)
        // CoroutineScope(Dispatchers.IO).launch {
        //    genreRepository.fetchGenres().collect()
        // }

        // ✅ Stetho 초기화
        Stetho.initializeWithDefaults(this)
    }

    // ✅ Stetho URL - chrome://inspect/#devices
}