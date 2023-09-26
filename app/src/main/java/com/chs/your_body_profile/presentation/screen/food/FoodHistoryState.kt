package com.chs.your_body_profile.presentation.screen.food

import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealType

data class FoodHistoryState(
    val mealType: MealType? = null,
    val takeMealList: List<FoodDetailInfo> = emptyList(),
    val totalCalories: Int = 0,
    val totalCarbohydrate: Float = 0f,
    val totalFat: Float = 0f,
    val totalProtein: Float = 0f
)