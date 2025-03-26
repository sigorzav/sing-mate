package com.sigorzav.singmate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sigorzav.singmate.common.auth.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", ">>> MainActivity Start")

        val accessToken = tokenManager.getAccessToken()
        val expiredAt = tokenManager.getExpiredAt()

        val isTokenValid = !accessToken.isNullOrBlank()
        val isNotExpired = expiredAt != null && Date().before(expiredAt)

        if (isTokenValid && isNotExpired) {
            Log.d("MainActivity", "✅ Token Valid >>> Move SongActivity")
            startActivity(Intent(this, SongActivity::class.java))
        } else {
            Log.d("MainActivity", "❌ No Token or Expiration >>> Move UserActivity")
            tokenManager.clearAccessToken()
            startActivity(Intent(this, UserActivity::class.java))
        }

        finish()
    }
}