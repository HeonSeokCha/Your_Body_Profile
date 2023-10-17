package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity

@Entity(
    tableName = "taken_meal_history",
    primaryKeys = ["takenDate", "takenMealType", "foodCode"]
)
data class TakenMealHistoryEntity(
    val takenDate: Long,
    val takenMealType: Int,
    val foodCode: String
)