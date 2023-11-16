package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity

@Entity(
    tableName = "taken_meal_history",
    primaryKeys = ["takenDate", "takenMealType"]
)
data class TakenMealHistoryEntity(
    val takenDate: Long,
    val takenMealType: Int
)