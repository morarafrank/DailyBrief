package com.morarafrank.dailybrief.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.morarafrank.dailybrief.ui.screens.FullNewsScreen
import com.morarafrank.dailybrief.ui.screens.HeadlinesScreen
import com.morarafrank.dailybrief.ui.screens.HomeScreen
import com.morarafrank.dailybrief.ui.screens.LatestNewsScreen

@Composable
fun NewsNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route,
        modifier = modifier
    ) {

        composable(
            route = Screens.FullNewsScreen.route
        ){
            FullNewsScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = Screens.HeadlinesScreen.route
        ) {
            HeadlinesScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = Screens.HomeScreen.route
        ) {
             HomeScreen(
                navigateToHeadlinesScreen = {
                    navController.navigate(Screens.HeadlinesScreen.route)
                },
                navigateToLatestNewsScreen = {
                    navController.navigate(Screens.LatestNewsScreen.route)
                },

                 navigateToSingleArticle = {
                        navController.navigate(Screens.FullNewsScreen.route)
                 }
             )
        }

        composable(
            route = Screens.LatestNewsScreen.route
        ){
            LatestNewsScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

    }

}