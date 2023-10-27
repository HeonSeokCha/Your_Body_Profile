package com.chs.your_body_profile.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chs.your_body_profile.common.toJsonStringEncode
import com.chs.your_body_profile.common.toLocalDate
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toFoodDetailInfo
import com.chs.your_body_profile.data.model.dto.ResponseFoodDetailInfo
import com.chs.your_body_profile.domain.model.FoodDetailInfo
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
import kotlinx.serialization.json.Json
import java.time.LocalDate

@Composable
fun MainNavHost(
    navController: NavHostController
) {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }

    NavHost(
        navController = navController,
        startDestination = Screens.ScreenBodyDash.route
    ) {
        composable(Screens.ScreenBodyDash.route) {
            BodyDashBoardScreen(navController)
        }

        composable(
            route = "${Screens.ScreenBloodSugarInput.route}?measureDate={measureDate}",
            arguments = listOf(
                navArgument("measureDate") {
                    type = NavType.LongType
                    defaultValue = LocalDate.now().toMillis()
                }
            )
        ) {
            BloodSugarInputScreen(
                measureDate = it.arguments?.getLong("measureDate")!!.toLocalDate(),
                navController = navController
            )
        }

        composable(
            route = "${Screens.ScreenBloodPressureInput.route}?measureDate={measureDate}",
            arguments = listOf(
                navArgument("measureDate") {
                    type = NavType.LongType
                    defaultValue = LocalDate.now().toMillis()
                }
            )
        ) {
            BloodPressureInputScreen(
                measureDate = it.arguments?.getLong("measureDate")!!.toLocalDate(),
                navController = navController
            )
        }

        composable(
            route = "${Screens.ScreenInsulinInput.route}?measureDate={measureDate}",
            arguments = listOf(
                navArgument("measureDate") {
                    type = NavType.LongType
                    defaultValue = LocalDate.now().toMillis()
                }
            )
        ) {
            InsulinInputScreen(
                measureDate = it.arguments?.getLong("measureDate")!!.toLocalDate(),
                navController = navController
            )
        }

        composable(
            route = "${Screens.ScreenHemoglobinA1cInput.route}?measureDate={measureDate}",
            arguments = listOf(
                navArgument("measureDate") {
                    type = NavType.LongType
                    defaultValue = LocalDate.now().toMillis()
                }
            )
        ) {
            HemoglobinA1cInputScreen(
                measureDate = it.arguments?.getLong("measureDate")!!.toLocalDate(),
                navController = navController
            )
        }

        composable(
            route = "${Screens.ScreenMedicineInput.route}?measureDate={measureDate}",
            arguments = listOf(
                navArgument("measureDate") {
                    type = NavType.LongType
                    defaultValue = LocalDate.now().toMillis()
                }
            )
        ) {

        }

        composable(Screens.ScreenMealList.route) {
            MealListScreen(navController)
        }

        composable(
            route = "${Screens.ScreenFoodSearch.route}/{mealType}?takenDate={takenDate}",
            arguments = listOf(
                navArgument("takenDate") {
                    type = NavType.LongType
                    defaultValue = LocalDate.now().toMillis()
                }
            )
        ) {
            val takenDate: Long = it.arguments?.getLong("takenDate")!!
            val mealType: String = it.arguments?.getString("mealType")!!
            FoodSearchScreen(
                takenDate = takenDate,
                mealType = mealType,
                navController
            )
        }

        composable(
            route = "${Screens.ScreenMealHistoryInput.route}/{takenDate}/{mealType}?foodList={foodList}",
            arguments = listOf(
                navArgument("takenDate") {
                    type = NavType.LongType
                    defaultValue = LocalDate.now().toMillis()
                },
                navArgument("foodList") {
                    type = NavType.StringType
                    defaultValue = listOf<FoodDetailInfo>().toJsonStringEncode()
                }
            )
        ) {
            val takenDate: Long = it.arguments?.getLong("takenDate")!!
            val mealType: MealType = MealType.values().find { mealType ->
                mealType.mean.second == it.arguments?.getString("mealType")!!
            } ?: MealType.MORNING

            val foodList = Json.decodeFromString<List<ResponseFoodDetailInfo>>(
                it.arguments?.getString("foodList")!!
            ).map { responseFoodDetailInfo ->
                responseFoodDetailInfo.toFoodDetailInfo()
            }

            MealHistoryInputScreen(
                takenDate = takenDate.toLocalDate(),
                takenMealType = mealType,
                foodList = foodList,
                navController = navController,
                viewModel = hiltViewModel(viewModelStoreOwner)
            )
        }

        composable(Screens.ScreenFoodDetail.route) {
            FoodDetailScreen(navController)
        }
    }
}