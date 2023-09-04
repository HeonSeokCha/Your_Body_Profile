package com.chs.your_body_profile.presentation.screen.body_dash_board

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Composable
fun BodyDashBoardScreen(
    navController: NavHostController,
    viewModel: BodyDashBoardViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle(initialValue = BodyDashBoardState())

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
            val todayLastFoodInfo: FoodInfo? = state.foodInfo
            val todayTotalCalorie: Int = state.totalCalorie
            FoodInfoDashBoard(
                title = stringResource(id = R.string.text_title_food),
                value = todayLastFoodInfo?.type?.mean?.second
                    ?: MealType.UNKNOWN.mean.second,
                subValue = todayLastFoodInfo?.name ?: "",
                subValue2 = todayTotalCalorie.toString()
            ) {

            }
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
            DashBoardInputCard(
                title = stringResource(id = R.string.text_blood_sugar),
                infoValue = "${state.bloodSugarInfo?.number ?: stringResource(id = R.string.text_default_measure_zero)}",
                infoUnit = stringResource(id = R.string.text_blood_sugar_unit),
                onClick = {
                          
                },
                btnClick = {
                    navController.navigate(Screens.InputBloodSugarScreen.route)
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
                    navController.navigate(Screens.InputInsulinScreen.route)
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
                    navController.navigate(Screens.InputBloodPressureScreen.route)
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
                    navController.navigate(Screens.InputHemoglobinA1cScreen.route)
                }
            )
        }
    }
}