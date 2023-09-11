package com.chs.your_body_profile.presentation.screen.body_dash_board

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.FoodInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.MedicineType
import com.chs.your_body_profile.presentation.Screens
import com.chs.your_body_profile.presentation.screen.food.MealTypeBottomSheet
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyDashBoardScreen(
    navController: NavHostController,
    viewModel: BodyDashBoardViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle(initialValue = BodyDashBoardState())
    val mealTypeSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (mealTypeSheetState.isVisible) {
        ModalBottomSheet(onDismissRequest = {
            scope.launch {
                mealTypeSheetState.hide()
            }
        }) {
            MealTypeBottomSheet {
                scope.launch {
                    mealTypeSheetState.hide()
                }
                navController.navigate("${Screens.ScreenFoodSearch.route}/$it")
            }
        }
    }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize(),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            val todayMedicineInfo: MedicineInfo? = state.medicineInfo
            MedicineInfoDashBoard(
                title = stringResource(id = R.string.text_title_take_medicine),
                value = todayMedicineInfo?.medicineType?.time?.second
                    ?: MedicineType.UNKNOWN.time.second,
                subValue = todayMedicineInfo?.title
                    ?: stringResource(id = R.string.text_take_medicine_default),
                subValue2 = todayMedicineInfo?.doseTime?.format(Constants.DATE_TIME_FORMATTER)
            ) {

            }
        }

        item {
            DrinkInfoDashBoard(
                title = stringResource(id = R.string.text_title_drink_water),
                value = state.drinkWaterInfo?.totalCups ?: 0,
                drinkEventClick = { totalCups ->
                    viewModel.updateDrinkWaterInfo(totalCups)
                }, cardClick = {

                }
            )
        }

        item {
            DrinkInfoDashBoard(
                title = stringResource(id = R.string.text_title_drink_coffee),
                value = state.drinkCoffeeInfo?.totalCups ?: 0,
                drinkEventClick = { totalCups ->
                    viewModel.updateDrinkCoffeeInfo(totalCups)
                },
                cardClick = {

                }
            )
        }

        item(span = StaggeredGridItemSpan.FullLine) {
            val todayTotalCalorie: Int = state.totalCalorie
            DashBoardInputCard(
                title = stringResource(id = R.string.text_title_food),
                infoValue = todayTotalCalorie.toString(),
                infoUnit = stringResource(id = R.string.text_food_unit),
                onClick = {
                    navController.navigate(Screens.ScreenMealList.route)
                },
                btnClick = {
                    scope.launch {
                        mealTypeSheetState.show()
                    }
                }
            )
        }

        item(span = StaggeredGridItemSpan.FullLine) {
            DashBoardInputCard(
                title = stringResource(id = R.string.text_blood_sugar),
                infoValue = "${state.bloodSugarInfo?.number ?: stringResource(id = R.string.text_default_measure_zero)}",
                infoUnit = stringResource(id = R.string.text_blood_sugar_unit),
                onClick = {
                          
                },
                btnClick = {
                    navController.navigate(Screens.ScreenBloodSugarInput.route)
                }
            )
        }

        item(span = StaggeredGridItemSpan.FullLine) {
            DashBoardInputCard(
                title = stringResource(id = R.string.text_insulin),
                infoValue = "${state.insulinInfo?.level ?: stringResource(id = R.string.text_default_measure_zero)}",
                infoUnit = stringResource(id = R.string.text_insulin_unit),
                onClick = {
                          
                },
                btnClick = {
                    navController.navigate(Screens.ScreenInsulinInput.route)
                }
            )
        }

        item(span = StaggeredGridItemSpan.FullLine) {
            val todayBloodPressureInfo: BloodPressureInfo? = state.bloodPressureInfo
            DashBoardInputCard(
                title = stringResource(id = R.string.text_blood_pressure),
                infoValue = if (todayBloodPressureInfo != null) {
                    "${todayBloodPressureInfo.systolic}/${todayBloodPressureInfo.diastolic}"
                } else {
                    stringResource(id = R.string.text_default_measure_unknown)
                },
                infoUnit = stringResource(id = R.string.text_blood_pressure_unit),
                onClick = {

                },
                btnClick = {
                    navController.navigate(Screens.ScreenBloodPressureInput.route)
                }
            )
        }
        item(span = StaggeredGridItemSpan.FullLine) {
            DashBoardInputCard(
                title = stringResource(id = R.string.text_hemoglobin_A1c),
                infoValue = "${state.hemoglobinA1cInfo?.number ?: stringResource(id = R.string.text_default_measure_zero)}",
                infoUnit = stringResource(id = R.string.text_hemoglobin_A1c_unit),
                onClick = {

                },
                btnClick = {
                    navController.navigate(Screens.ScreenHemoglobinA1cInput.route)
                }
            )
        }
    }
}