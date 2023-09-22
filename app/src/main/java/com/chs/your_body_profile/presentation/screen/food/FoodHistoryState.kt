package com.chs.your_body_profile.presentation.screen.food

import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealType

data class FoodHistoryState(
    val mealType: MealType? = null,
    val takeFoodList: List<FoodDetailInfo> = emptyList(),
)
