package com.chs.your_body_profile.presentation.screen.food

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.chs.your_body_profile.presentation.common.ItemVerticalChart
import com.chs.your_body_profile.presentation.common.toDecimalPlace

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

        Spacer(modifier = Modifier.height(16.dp))
        
        Text(text = "${state.dayTakenMealList.map { it.second.map { it.calorie}.sum() }.sum().toInt()} kcal")

        Spacer(modifier = Modifier.height(16.dp))

        state.dayTakenMealList.forEach {
            Row {
                Column(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "${it.second.map { it.calorie }.sum().toInt()}")
                    Text(text = "kcal")
                }
                Column(
                    modifier = Modifier
                        .padding(start = 18.dp)
                ) {
                    Text(text = it.first.mealType.mean.second)
                    Row {
                        it.second.forEach {
                            Text(text = it.name)
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        if (state.dayTakenMealList.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "탄수화물 : (${state.dayTakenMealList.map { it.second.map { it.carbohydrate}.sum() }.sum().toDecimalPlace()}) g")
            Text(text = "지방 : (${state.dayTakenMealList.map { it.second.map { it.fat}.sum() }.sum().toDecimalPlace()}) g")
            Text(text = "단백질 : (${state.dayTakenMealList.map { it.second.map { it.protein}.sum() }.sum().toDecimalPlace()}) g")
        }
    }
}