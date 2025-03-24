package com.sigorzav.singmate.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object Home : BottomNavItem(Routes.SONG_HOME, Icons.Default.Home, "홈")
    data object Search : BottomNavItem(Routes.SONG_SEARCH, Icons.Default.Search, "검색")
    data object Record : BottomNavItem(Routes.SONG_RECORD, Icons.Default.Mic, "녹음")
    data object Library : BottomNavItem(Routes.SONG_LIBRARY, Icons.Default.LibraryMusic, "보관함")
    data object MyPage : BottomNavItem(Routes.SONG_MYPAGE, Icons.Default.Person, "내정보")
}