package com.chs.your_body_profile.presentation.screen.food

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealType
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

data class FoodSearchState(
    val mealType: String? = null,
    val searchQuery: String = "",
    val searchHistory: List<String> = emptyList(),
    val searchResult: Flow<PagingData<FoodDetailInfo>>? = null,
    val recentFoodList: List<FoodDetailInfo> = emptyList(),
    val selectFoodList: List<FoodDetailInfo> = emptyList(),
    val isError: String? = null,
    val isLoading: Boolean = false
)