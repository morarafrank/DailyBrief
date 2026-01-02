package com.morarafrank.dailybrief.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.morarafrank.dailybrief.ui.navigation.NewsNavGraph
import com.morarafrank.dailybrief.ui.theme.DailyBriefTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyBriefTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()
                    NewsNavGraph(
                        navController = navController
                    )
                }
            }
        }
    }
}