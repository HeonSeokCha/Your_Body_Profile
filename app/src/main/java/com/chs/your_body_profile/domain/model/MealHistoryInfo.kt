package com.chs.your_body_profile.domain.model

import java.time.LocalDateTime

data class MealHistoryInfo(
    val takenDateTime: LocalDateTime,
    val measureTime: LocalDateTime,
    val mealName: String,
    val mealType: MealType
)