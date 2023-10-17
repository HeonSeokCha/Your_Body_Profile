package com.chs.your_body_profile.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

data class TakenMealInfo(
    val takenDate: LocalDate,
    val takenTime: LocalDateTime,
    val mealType: MealType
)
