package com.chs.your_body_profile.presentation.screen.food

import com.chs.your_body_profile.domain.model.FoodDetailInfo

data class FoodSearchState(
    val searchQuery: String? = null,
    val searchHistory: List<String> = emptyList(),
    val selectItems: List<String> = emptyList(),
    val recentFoodList: List<FoodDetailInfo> = emptyList(),
    val favoriteFoodList: List<FoodDetailInfo> = emptyList(),
    val isError: String? = null,
    val isLoading: Boolean = false
)
