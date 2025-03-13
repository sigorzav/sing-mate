package com.sigorzav.singmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sigorzav.singmate.ui.song.MusicSearchScreen
import com.sigorzav.singmate.viewmodel.song.MusicSearchViewModel

class SongActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val viewModel: MusicSearchViewModel = viewModel()

            NavHost(navController = navController, startDestination = "music_search") {
                composable("music_search") {
                    MusicSearchScreen(viewModel = viewModel)
                }
            }
        }
    }
}