package com.chs.your_body_profile.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chs.your_body_profile.presentation.screen.blood_pressure.BloodPressureInputScreen
import com.chs.your_body_profile.presentation.screen.blood_sugar.BloodSugarInputScreen
import com.chs.your_body_profile.presentation.screen.body_dash_board.BodyDashBoardScreen
import com.chs.your_body_profile.presentation.screen.food.FoodDetailScreen
import com.chs.your_body_profile.presentation.screen.food.FoodSearchScreen
import com.chs.your_body_profile.presentation.screen.food.MealInputScreen
import com.chs.your_body_profile.presentation.screen.food.MealListScreen
import com.chs.your_body_profile.presentation.screen.hemoglobinA1c.HemoglobinA1cInputScreen
import com.chs.your_body_profile.presentation.screen.insulin.InsulinInputScreen

@Composable
fun MainNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.ScreenBodyDash.route
    ) {

        composable(Screens.ScreenBodyDash.route) {
            BodyDashBoardScreen(navController)
        }

        composable(Screens.ScreenBloodSugarInput.route) {
            BloodSugarInputScreen(navController)
        }

        composable(Screens.ScreenBloodPressureInput.route) {
            BloodPressureInputScreen(navController)
        }

        composable(Screens.ScreenInsulinInput.route) {
            InsulinInputScreen(navController)
        }

        composable(Screens.ScreenHemoglobinA1cInput.route) {
            HemoglobinA1cInputScreen(navController)
        }

        composable(Screens.ScreenMedicineInput.route) {

        }

        composable(Screens.ScreenMealInput.route) {
            MealInputScreen(navController)
        }

        composable(Screens.ScreenMealList.route) {
            MealListScreen(navController)
        }

        composable(Screens.ScreenFoodSearch.route) {
            FoodSearchScreen(navController)
        }

        composable(Screens.ScreenFoodDetail.route) {
            FoodDetailScreen(navController)
        }
    }
}