package com.chs.your_body_profile.presentation.screen.food

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealHistoryInfo

@Composable
fun MealHistoryInputScreen(
    takenMealHistoryInfo: MealHistoryInfo,
    foodList: List<FoodDetailInfo>,
    viewModel: MealHistoryInputViewModel = hiltViewModel()
) {
    Column {
        foodList.forEach {
            Text(text = it.name)
        }
    }
}