package com.chs.your_body_profile.domain.model

import java.time.LocalDate

data class FoodInfo(
    val measureTime: LocalDate,
    val name: String,
    val type: MealType,
    val calorie: Int,
)
