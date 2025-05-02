package com.chs.your_body_profile.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.chs.your_body_profile.presentation.screen.blood_pressure.BloodPressureInputScreen
import com.chs.your_body_profile.presentation.screen.blood_sugar.BloodSugarInputScreen
import com.chs.your_body_profile.presentation.screen.body_dash_board.BodyDashBoardScreen
import com.chs.your_body_profile.presentation.screen.food.FoodDetailScreen
import com.chs.your_body_profile.presentation.screen.food.MealListScreen
import com.chs.your_body_profile.presentation.screen.hemoglobinA1c.HemoglobinA1cInputScreen
import com.chs.your_body_profile.presentation.screen.insulin.InsulinInputScreen

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.ScreenBodyDash
    ) {
        composable<Screens.ScreenBodyDash> {
            BodyDashBoardScreen(navController)
        }

        composable<Screens.ScreenBloodSugarInput> {
            val arg = it.toRoute<Screens.ScreenBloodSugarInput>()
            val parentEntry = remember(it) {
                navController.getBackStackEntry(arg)
            }

            BloodSugarInputScreen(
                navController = navController
            )
        }

        composable<Screens.ScreenBloodPressureInput> {
            val arg = it.toRoute<Screens.ScreenBloodPressureInput>()
            val parentEntry = remember(it) {
                navController.getBackStackEntry(arg)
            }
            BloodPressureInputScreen(
                navController = navController
            )
        }

        composable<Screens.ScreenInsulinInput> {
            val arg = it.toRoute<Screens.ScreenInsulinInput>()
            val parentEntry = remember(it) {
                navController.getBackStackEntry(arg)
            }
            InsulinInputScreen(
                navController = navController
            )
        }

        composable<Screens.ScreenHemoglobinA1cInput> {
            val arg = it.toRoute<Screens.ScreenHemoglobinA1cInput>()
            val parentEntry = remember(it) {
                navController.getBackStackEntry(arg)
            }
            HemoglobinA1cInputScreen(
                navController = navController
            )
        }

        composable<Screens.ScreenMedicineInput> {
            val arg = it.toRoute<Screens.ScreenMedicineInput>()
            val parentEntry = remember(it) {
                navController.getBackStackEntry(arg)
            }
            MealListScreen(navController)
        }

        composable<Screens.ScreenFoodDetail> {
            FoodDetailScreen(navController)
        }
    }
}