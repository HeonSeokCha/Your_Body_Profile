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
import com.chs.your_body_profile.domain.model.MealType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

@Composable
fun MealHistoryInputScreen(
    takenDate: LocalDate,
    takenMealType: MealType,
    foodList: List<FoodDetailInfo>,
    viewModel: MealHistoryInputViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(context, viewModel) {
        withContext(Dispatchers.IO) {
            viewModel.initMealHistoryInfo(
                takenDate = takenDate,
                takenMealType = takenMealType,
                foodList = foodList
            )
        }
    }

    Column {
        foodList.forEach {
            Text(text = it.name)
        }
    }
}