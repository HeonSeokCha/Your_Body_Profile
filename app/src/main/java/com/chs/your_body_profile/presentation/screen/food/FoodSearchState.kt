package com.chs.your_body_profile.presentation.screen.food

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import kotlinx.coroutines.flow.Flow

data class FoodSearchState(
    val mealType: String = "",
    val takenDate: Long = 0L,
    val searchQuery: String = "",
    val searchHistory: List<String> = emptyList(),
    val searchResult: Flow<PagingData<FoodDetailInfo>>? = null,
    val recentFoodList: List<FoodDetailInfo> = emptyList(),
    val selectFoodList: List<FoodDetailInfo> = emptyList(),
    val isError: String? = null,
    val isLoading: Boolean = false
)