package com.chs.your_body_profile.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TakenMealHistoryInfo(
    val takenDate: Long = System.currentTimeMillis(),
    val takenTime: Long = System.currentTimeMillis(),
    val mealType: Int = 0
)
