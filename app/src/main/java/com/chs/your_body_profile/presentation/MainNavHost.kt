package com.chs.your_body_profile.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.chs.your_body_profile.presentation.screen.blood_pressure.BloodPressureInputScreen
import com.chs.your_body_profile.presentation.screen.blood_sugar.BloodSugarInputScreen
import com.chs.your_body_profile.presentation.screen.blood_sugar.BloodSugarInputScreenRoot
import com.chs.your_body_profile.presentation.screen.blood_sugar.BloodSugarInputViewModel
import com.chs.your_body_profile.presentation.screen.drink.DrinkListScreen
import com.chs.your_body_profile.presentation.screen.drink.DrinkListScreenRoot
import com.chs.your_body_profile.presentation.screen.drink.DrinkViewModel
import com.chs.your_body_profile.presentation.screen.food.FoodDetailScreen
import com.chs.your_body_profile.presentation.screen.food.MealListScreen
import com.chs.your_body_profile.presentation.screen.hemoglobinA1c.HemoglobinA1cInputScreen
import com.chs.your_body_profile.presentation.screen.home.HomeScreenRoot
import com.chs.your_body_profile.presentation.screen.home.HomeViewModel

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home
    ) {
        composable<Screens.Home> {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreenRoot(viewModel) {
                navController.navigate(it)
            }
        }

        composable<Screens.DrinkList> {
            val viewModel: DrinkViewModel = hiltViewModel()
            DrinkListScreenRoot(viewModel) {

            }
        }

        composable<Screens.BloodSugarInput> {
            val arg = it.toRoute<Screens.BloodSugarInput>()
            val parentEntry = remember(it) {
                navController.getBackStackEntry(arg)
            }
            val viewModel: BloodSugarInputViewModel = hiltViewModel(parentEntry)

            BloodSugarInputScreenRoot(viewModel) {

            }
        }

        composable<Screens.BloodPressureInput> {
            val arg = it.toRoute<Screens.BloodPressureInput>()
            val parentEntry = remember(it) {
                navController.getBackStackEntry(arg)
            }
            BloodPressureInputScreen(
                navController = navController
            )
        }

        composable<Screens.HemoglobinA1cInput> {
            val arg = it.toRoute<Screens.HemoglobinA1cInput>()
            val parentEntry = remember(it) {
                navController.getBackStackEntry(arg)
            }
            HemoglobinA1cInputScreen(
                navController = navController
            )
        }

        composable<Screens.MedicineInput> {
            val arg = it.toRoute<Screens.MedicineInput>()
            val parentEntry = remember(it) {
                navController.getBackStackEntry(arg)
            }
            MealListScreen(navController)
        }

        composable<Screens.FoodDetail> {
            FoodDetailScreen(navController)
        }
    }
}