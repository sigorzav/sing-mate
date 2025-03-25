package com.sigorzav.singmate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sigorzav.singmate.common.auth.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val accessToken = tokenManager.getAccessToken()

        if (accessToken.isNullOrBlank()) {
            startActivity(Intent(this, UserActivity::class.java))
        } else {
            startActivity(Intent(this, SongActivity::class.java))
        }

        finish()
    }
}