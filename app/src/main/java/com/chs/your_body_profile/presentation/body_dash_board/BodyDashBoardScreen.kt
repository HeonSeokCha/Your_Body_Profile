package com.chs.your_body_profile.presentation.body_dash_board

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
import com.chs.your_body_profile.R

@Composable
fun BodyDashBoardScreen(
    viewModel: BodyDashBoardViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle(initialValue = BodyDashBoardState())

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
        modifier = Modifier.fillMaxSize(),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {

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
            DashBoardInputCard(
                title = stringResource(id = R.string.text_blood_sugar),
                infoValue = "${state.bloodSugarInfo?.number ?: stringResource(id = R.string.text_default_measure_unknown)}",
                infoUnit = stringResource(id = R.string.text_blood_sugar_unit),
                onClick = {
                          
                },
                btnClick = {
                    
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
                    
                }
            )
        }
        item(span = StaggeredGridItemSpan.FullLine) {

        }
        item(span = StaggeredGridItemSpan.FullLine) {

        }

    }
}