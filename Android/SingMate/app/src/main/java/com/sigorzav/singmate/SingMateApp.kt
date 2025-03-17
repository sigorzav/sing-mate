package com.sigorzav.singmate

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SingMateApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // ✅ Stetho 초기화
        Stetho.initializeWithDefaults(this)
    }

    // ✅ Stetho URL - chrome://inspect/#devices
}