package com.chs.your_body_profile.presentation.screen.home

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toCommaFormat
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.model.PaymentInfo
import com.chs.your_body_profile.presentation.Screens

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
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2), contentPadding = PaddingValues(8.dp), modifier = Modifier.fillMaxSize(),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            val lastPaymentInfo: PaymentInfo? = state.payInfo
            LongInputCard(
                title = stringResource(id = R.string.text_title_payment),
                value = lastPaymentInfo?.title ?: stringResource(R.string.text_no_items),
                subValue = "${(lastPaymentInfo?.amount ?: 0).toCommaFormat()}${stringResource(R.string.text_payment_unit)}",
                subValue2 = lastPaymentInfo?.paymentTime?.format(Constants.DATE_FORMATTER_DETAIL)
            ) {
                onEvent(HomeEvent.Navigate(Screens.PayList))
            }
        }

        item {
            UpDownInputSmallCard(
                title = stringResource(id = R.string.text_title_drink_water),
                value = state.drinkWaterTotalCupInfo.count(),
                onDownClick = {
                    onEvent(HomeEvent.Update.Down.Water)
                },
                onUpClick = {
                    onEvent(HomeEvent.Update.Up.Water)
                },
                cardClick = {
                    onEvent(HomeEvent.Navigate(Screens.DrinkList(DrinkType.WATER)))
                }
            )
        }

        item {
            UpDownInputSmallCard(
                title = stringResource(id = R.string.text_title_drink_coffee),
                value = state.drinkCoffeeTotalCupInfo.count(),
                onDownClick = {
                    onEvent(HomeEvent.Update.Down.Coffee)
                },
                onUpClick = {
                    onEvent(HomeEvent.Update.Up.Coffee)
                },
                cardClick = {
                    onEvent(HomeEvent.Navigate(Screens.DrinkList(DrinkType.COFFEE)))
                }
            )
        }

//        item(span = StaggeredGridItemSpan.FullLine) {
//            val todayTotalCalorie: FoodDetailInfo? = state.takenFoodInfo
//            DashBoardSingleLine(
//                title = stringResource(id = R.string.text_title_food),
//                infoValue = (todayTotalCalorie ?: 0).toString(),
//                infoUnit = stringResource(id = R.string.text_food_unit),
//                onClick = {
//                },
//                btnClick = { isShowMealTypeDialog = true }
//            )
//        }

        item(span = StaggeredGridItemSpan.FullLine) {
            DashBoardSingleLineCard(
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
            DashBoardSingleLineCard(
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
            DashBoardSingleLineCard(
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
            DashBoardSingleLineCard(
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
            DashBoardSingleLineCard(
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