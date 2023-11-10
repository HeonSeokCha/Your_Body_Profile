package com.chs.your_body_profile.presentation.screen.food

import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealType
import java.time.LocalDate
import java.time.LocalDateTime

data class MealHistoryInputState(
    val takenDate: LocalDate = LocalDate.now(),
    val takenTime: LocalDateTime = LocalDateTime.now(),
    val mealType: MealType? = null,
    val previousMealHistory: List<FoodDetailInfo> = emptyList(),
    val takenFoodList: List<FoodDetailInfo> = emptyList(),
    val totalCalorie: Int = 0,
    val totalCarbohydrate: Float = 0f,
    val totalFat: Float = 0f,
    val totalProtein: Float = 0f
)
