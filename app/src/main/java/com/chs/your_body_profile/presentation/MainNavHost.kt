package com.chs.your_body_profile.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chs.your_body_profile.presentation.screen.blood_pressure.input.BloodPressureInputScreenRoot
import com.chs.your_body_profile.presentation.screen.blood_pressure.input.BloodPressureInputViewModel
import com.chs.your_body_profile.presentation.screen.blood_sugar.input.BloodSugarInputScreenRoot
import com.chs.your_body_profile.presentation.screen.blood_sugar.input.BloodSugarInputViewModel
import com.chs.your_body_profile.presentation.screen.drink.DrinkListScreenRoot
import com.chs.your_body_profile.presentation.screen.drink.DrinkViewModel
import com.chs.your_body_profile.presentation.screen.food.FoodDetailScreen
import com.chs.your_body_profile.presentation.screen.food.MealListScreen
import com.chs.your_body_profile.presentation.screen.hemoglobinA1c.input.HemoglobinA1cInputScreenRoot
import com.chs.your_body_profile.presentation.screen.hemoglobinA1c.input.HemoglobinA1cInputViewModel
import com.chs.your_body_profile.presentation.screen.home.HomeScreenRoot
import com.chs.your_body_profile.presentation.screen.home.HomeViewModel
import com.chs.your_body_profile.presentation.screen.insulin.input.InsulinInputScreenRoot
import com.chs.your_body_profile.presentation.screen.insulin.input.InsulinInputViewModel

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
            val viewModel: BloodSugarInputViewModel = hiltViewModel()
            BloodSugarInputScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screens.BloodPressureInput> {
            val viewModel: BloodPressureInputViewModel = hiltViewModel()
            BloodPressureInputScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screens.HemoglobinA1cInput> {
            val viewModel: HemoglobinA1cInputViewModel = hiltViewModel()
            HemoglobinA1cInputScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screens.InsulinInput> {
            val viewModel: InsulinInputViewModel = hiltViewModel()
            InsulinInputScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Screens.MedicineInput> {
            MealListScreen(navController)
        }

        composable<Screens.FoodDetail> {
            FoodDetailScreen(navController)
        }
    }
}