package com.chs.your_body_profile.presentation.screen.food

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import kotlinx.coroutines.flow.Flow

data class FoodSearchState(
    val searchQuery: String = "",
    val searchHistory: List<String> = emptyList(),
    val selectItems: MutableList<String> = mutableListOf(),
    val searchResult: Flow<PagingData<FoodDetailInfo>>? = null,
    val recentFoodList: List<FoodDetailInfo> = emptyList(),
    val isError: String? = null,
    val isLoading: Boolean = false
)
