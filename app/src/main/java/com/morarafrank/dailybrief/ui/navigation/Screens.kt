package com.morarafrank.dailybrief.ui.navigation

sealed class Screens (val route: String) {
    object HomeScreen: Screens("home_screen")
    object LatestNewsScreen: Screens("latest_news_screen")
    object HeadlinesScreen: Screens("headlines_screen")
    object FullNewsScreen: Screens("full_news_screen")
}