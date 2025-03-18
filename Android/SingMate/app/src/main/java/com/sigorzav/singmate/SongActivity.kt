package com.sigorzav.singmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sigorzav.singmate.ui.song.SongSearchScreen

import com.sigorzav.singmate.viewmodel.song.SongSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val viewModel: SongSearchViewModel = hiltViewModel()

            NavHost(navController = navController, startDestination = "song_search") {
                composable("song_search") {
                    SongSearchScreen(viewModel = viewModel)
                }
            }
        }
    }
}