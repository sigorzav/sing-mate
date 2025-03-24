package com.sigorzav.singmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sigorzav.singmate.ui.navigation.BottomNavItem
import com.sigorzav.singmate.ui.navigation.BottomNavigationBar
import com.sigorzav.singmate.ui.navigation.Routes
import com.sigorzav.singmate.ui.song.SongDetailScreen
import com.sigorzav.singmate.ui.song.SongHomeScreen
import com.sigorzav.singmate.ui.song.SongLibraryScreen
import com.sigorzav.singmate.ui.song.SongMyPageScreen
import com.sigorzav.singmate.ui.song.SongRecordScreen
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

            val bottomNavItems = listOf(
                BottomNavItem.Home,
                BottomNavItem.Search,
                BottomNavItem.Record,
                BottomNavItem.Library,
                BottomNavItem.MyPage
            )

            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        items = bottomNavItems
                    )
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Routes.SONG_HOME,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Routes.SONG_HOME) {
                        SongHomeScreen(viewModel = songHomeViewModel)
                    }
                    composable(Routes.SONG_SEARCH) {
                        SongSearchScreen(viewModel = songSearchViewModel)
                    }
                    composable(Routes.SONG_DETAIL) {
                        SongDetailScreen()
                    }
                    composable(Routes.SONG_RECORD) {
                        SongRecordScreen()
                    }
                    composable(Routes.SONG_LIBRARY) {
                        SongLibraryScreen()
                    }
                    composable(Routes.SONG_MYPAGE) {
                        SongMyPageScreen()
                    }
                }
            }
        }
    }
}