package com.chs.your_body_profile.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.MedicineType
import com.chs.your_body_profile.presentation.Screens
import com.chs.your_body_profile.presentation.common.ItemMealTypeAlertDialog
import java.time.LocalDateTime

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel,
    onNavigate: (Screens) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(state) { event ->
        when(event) {
            is HomeEvent.Navigate -> { onNavigate(event.target) }

            else -> viewModel.changeEvent(event)
        }
    }
}

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    var isShowMealTypeDialog by remember { mutableStateOf(false) }

    if (isShowMealTypeDialog) {
        ItemMealTypeAlertDialog(
            onDisMiss = { isShowMealTypeDialog = it }
        ) {
            isShowMealTypeDialog = false
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
                subValue2 = todayMedicineInfo?.takenDateTime?.format(Constants.DATE_TIME_FORMATTER)
            ) {

            }
        }

        item {
            DrinkInfoDashBoard(
                title = stringResource(id = R.string.text_title_drink_water),
                value = state.drinkWaterInfo?.totalCups ?: 0,
                drinkEventClick = { totalCups ->
                    onEvent(HomeEvent.Update.Water(totalCups))
                }, cardClick = {

                }
            )
        }

        item {
            DrinkInfoDashBoard(
                title = stringResource(id = R.string.text_title_drink_coffee),
                value = state.drinkCoffeeInfo?.totalCups ?: 0,
                drinkEventClick = { totalCups ->
                    onEvent(HomeEvent.Update.Coffee(totalCups))
                },
                cardClick = {

                }
            )
        }

        item(span = StaggeredGridItemSpan.FullLine) {
            val todayTotalCalorie: FoodDetailInfo? = state.takenFoodInfo
            DashBoardInputCard(
                title = stringResource(id = R.string.text_title_food),
                infoValue = (todayTotalCalorie ?: 0).toString(),
                infoUnit = stringResource(id = R.string.text_food_unit),
                onClick = {
                },
                btnClick = { isShowMealTypeDialog = true }
            )
        }

        item(span = StaggeredGridItemSpan.FullLine) {
            DashBoardInputCard(
                title = stringResource(id = R.string.text_weight),
                infoValue = "${state.weightInfo?.weight ?: stringResource(id = R.string.text_default_measure_zero)}",
                infoUnit = stringResource(id = R.string.text_weight_unit),
                onClick = { onEvent(HomeEvent.Navigate(Screens.WeightList)) },
                btnClick = {
                    onEvent(HomeEvent.Navigate(Screens.WeightInput(state.weightInfo?.weight)))
                }
            )
        }

        item(span = StaggeredGridItemSpan.FullLine) {
            DashBoardInputCard(
                title = stringResource(id = R.string.text_blood_sugar),
                infoValue = "${state.bloodSugarInfo?.number ?: stringResource(id = R.string.text_default_measure_zero)}",
                infoUnit = stringResource(id = R.string.text_blood_sugar_unit),
                onClick = {
                    onEvent(HomeEvent.Navigate(Screens.BloodSugarList))
                },
                btnClick = {
                    onEvent(HomeEvent.Navigate(Screens.BloodSugarInput(state.bloodSugarInfo?.number)))
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
                onClick = { onEvent(HomeEvent.Navigate(Screens.BloodPressureList)) },
                btnClick = {
                    onEvent(
                        HomeEvent.Navigate(
                            Screens.BloodPressureInput(
                                diastolic = todayBloodPressureInfo?.diastolic,
                                systolic = todayBloodPressureInfo?.systolic
                            )
                        )
                    )
                }
            )
        }
        item(span = StaggeredGridItemSpan.FullLine) {
            DashBoardInputCard(
                title = stringResource(id = R.string.text_hemoglobin_A1c),
                infoValue = "${state.hemoglobinA1cInfo?.number ?: stringResource(id = R.string.text_default_measure_zero)}",
                infoUnit = stringResource(id = R.string.text_hemoglobin_A1c_unit),
                onClick = { onEvent(HomeEvent.Navigate(Screens.HemoglobinA1cList)) },
                btnClick = {
                    onEvent(HomeEvent.Navigate(Screens.HemoglobinA1cInput(state.hemoglobinA1cInfo?.number)))
                }
            )
        }

        item(span = StaggeredGridItemSpan.FullLine) {
            DashBoardInputCard(
                title = stringResource(id = R.string.text_insulin),
                infoValue = "${state.insulinInfo?.level ?: stringResource(id = R.string.text_default_measure_zero)}",
                infoUnit = stringResource(id = R.string.text_insulin_unit),
                onClick = { onEvent(HomeEvent.Navigate(Screens.InsulinList)) },
                btnClick = {
                    onEvent(HomeEvent.Navigate(Screens.InsulinInput(state.insulinInfo?.level)))
                }
            )
        }
    }
}