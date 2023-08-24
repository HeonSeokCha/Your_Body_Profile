package com.chs.your_body_profile.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chs.your_body_profile.presentation.screen.blood_pressure.BloodPressureInputScreen
import com.chs.your_body_profile.presentation.screen.blood_sugar.BloodSugarInputScreen
import com.chs.your_body_profile.presentation.screen.body_dash_board.BodyDashBoardScreen
import com.chs.your_body_profile.presentation.screen.insulin.InsulinInputScreen

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
            BloodSugarInputScreen(navController)
        }

        composable(Screens.InputBloodPressureScreen.route) {
            BloodPressureInputScreen(navController)
        }

        composable(Screens.InputInsulinScreen.route) {
            InsulinInputScreen(navController)
        }

        composable(Screens.InputHemoglobinA1cScreen.route) {

        }

        composable(Screens.InputMedicineScreen.route) {

        }

        composable(Screens.InputFoodScreen.route) {

        }
    }
}