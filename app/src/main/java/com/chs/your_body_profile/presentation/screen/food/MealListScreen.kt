package com.chs.your_body_profile.presentation.screen.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.chs.your_body_profile.presentation.common.ItemVerticalChart

@Composable
fun MealListScreen(
    navController: NavHostController,
    viewModel: MealListViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val pagingItems = state.chartList?.collectAsLazyPagingItems()

    Column(
        Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        ItemVerticalChart(pagingItems) {
            viewModel.getDayTakenMealInfo(it)
        }

        state.dayTakenMealList.forEach {
            Row {
                Text(text = it.first.mealType.mean.second)
                Column {
                    it.second.forEach {
                        Text(text = it.name)
                    }
                }
            }
        }
    }
}