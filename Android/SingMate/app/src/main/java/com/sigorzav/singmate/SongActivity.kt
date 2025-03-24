package com.sigorzav.singmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sigorzav.singmate.ui.navigation.Routes
import com.sigorzav.singmate.ui.song.SongHomeScreen
import com.sigorzav.singmate.ui.song.SongSearchScreen
import com.sigorzav.singmate.viewmodel.song.SongHomeViewModel
import com.sigorzav.singmate.viewmodel.song.SongSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val songHomeViewModel: SongHomeViewModel = hiltViewModel()
            val songSearchViewModel: SongSearchViewModel = hiltViewModel()

            NavHost(navController = navController, startDestination = Routes.SONG_HOME) {
                composable(Routes.SONG_HOME) {
                    SongHomeScreen(viewModel = songHomeViewModel)
                }
                composable(Routes.SONG_SEARCH) {
                    SongSearchScreen(viewModel = songSearchViewModel)
                }
            }
        }
    }
}