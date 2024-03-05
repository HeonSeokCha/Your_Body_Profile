package com.chs.your_body_profile.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

data class MealHistoryInfo(
    val takenDateTime: LocalDateTime,
    val foodList: List<FoodDetailInfo>,
    val mealType: MealType,
)