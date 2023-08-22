package com.chs.your_body_profile.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chs.your_body_profile.presentation.screen.blood_sugar.InputBloodSugarScreen
import com.chs.your_body_profile.presentation.screen.body_dash_board.BodyDashBoardScreen

@Composable
fun MainNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.BodyDashScreen.route
    ) {

        composable(Screens.BodyDashScreen.route) {
            BodyDashBoardScreen(navController)
        }

        composable(Screens.InputBloodSugarScreen.route) {
            InputBloodSugarScreen(navController)
        }
    }
}