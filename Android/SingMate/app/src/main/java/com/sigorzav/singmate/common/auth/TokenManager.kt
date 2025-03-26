package com.sigorzav.singmate.common.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPrefs: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            context,
            "secure_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveAccessToken(token: String) {
        sharedPrefs.edit().putString("access_token", token).apply()
    }

    fun saveExpiredAt(date: Date) {
        sharedPrefs.edit().putLong("expired_at", date.time).apply() // Date → Long
    }

    fun getAccessToken(): String? {
        return sharedPrefs.getString("access_token", null)
    }

    fun clearAccessToken() {
        sharedPrefs.edit().remove("access_token").apply()
    }

    fun getExpiredAt(): Date? {
        val millis = sharedPrefs.getLong("expired_at", -1L)
        return if (millis > 0) Date(millis) else null
    }
}