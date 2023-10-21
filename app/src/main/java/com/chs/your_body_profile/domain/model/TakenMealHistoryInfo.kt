package com.chs.your_body_profile.domain.model

import java.time.LocalDate

data class TakenMealHistoryInfo(
    val takenDate: LocalDate,
    val mealType: MealType,
    val foodCode: String
)