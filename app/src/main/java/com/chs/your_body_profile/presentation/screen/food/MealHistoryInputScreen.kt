package com.chs.your_body_profile.presentation.screen.food

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealHistoryInfo

@Composable
fun MealHistoryInputScreen(
    takenMealHistoryInfo: MealHistoryInfo,
    foodList: List<FoodDetailInfo>,
    viewModel: MealHistoryInputViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(context, viewModel) {
        viewModel.initMealHistoryInfo(takenMealHistoryInfo)
    }

    Column {
        foodList.forEach {
            Text(text = it.name)
        }
    }
}