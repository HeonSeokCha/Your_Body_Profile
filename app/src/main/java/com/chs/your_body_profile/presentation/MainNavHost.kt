package com.chs.your_body_profile.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chs.your_body_profile.data.mapper.toFoodDetailInfo
import com.chs.your_body_profile.data.mapper.toResponseFoodDetailInfo
import com.chs.your_body_profile.data.model.dto.ResponseFoodDetailInfo
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.presentation.screen.blood_pressure.BloodPressureInputScreen
import com.chs.your_body_profile.presentation.screen.blood_sugar.BloodSugarInputScreen
import com.chs.your_body_profile.presentation.screen.body_dash_board.BodyDashBoardScreen
import com.chs.your_body_profile.presentation.screen.food.FoodDetailScreen
import com.chs.your_body_profile.presentation.screen.food.FoodSearchScreen
import com.chs.your_body_profile.presentation.screen.food.MealHistoryInputScreen
import com.chs.your_body_profile.presentation.screen.food.MealListScreen
import com.chs.your_body_profile.presentation.screen.hemoglobinA1c.HemoglobinA1cInputScreen
import com.chs.your_body_profile.presentation.screen.insulin.InsulinInputScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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

        composable(Screens.ScreenMealList.route) {
            MealListScreen(navController)
        }

        composable(
            route = "${Screens.ScreenFoodSearch.route}/{mealType}",
            arguments = listOf(
                navArgument("mealType") {
                    type = NavType.StringType
                    defaultValue = MealType.MORNING.mean.second
                }
            )
        ) {
            FoodSearchScreen(
                it.arguments?.getString("mealType")!!,
                navController,
                navigateToMealHistoryInputScreen = { mealHistoryInfo, foodList ->
                    val jsonFoodList = Json.encodeToString(
                        foodList.map { foodInfo ->
                            foodInfo.toResponseFoodDetailInfo()
                        }
                    )
                    navController.navigate(
                        "${Screens.ScreenMealHistoryInput.route}/$jsonFoodList"
                    )
                }
            )
        }

        composable(
            route = "${Screens.ScreenMealHistoryInput.route}/{takenMealInfo}/{foodList}",
            arguments = listOf(
                navArgument("takenMealInfo") {
                    type = NavType.StringType
                },
                navArgument("foodList") {
                    type = NavType.StringType
                    defaultValue = Json.encodeToString(listOf<ResponseFoodDetailInfo>())
                }
            )
        ) {
            val foodList = Json.decodeFromString<List<ResponseFoodDetailInfo>>(
                it.arguments?.getString("foodList")!!
            ).map { responseFoodDetailInfo ->
                responseFoodDetailInfo.toFoodDetailInfo()
            }

            MealHistoryInputScreen(
                takenMealHistoryInfo =
                foodList = foodList
            )
        }

        composable(Screens.ScreenFoodDetail.route) {
            FoodDetailScreen(navController)
        }
    }
}