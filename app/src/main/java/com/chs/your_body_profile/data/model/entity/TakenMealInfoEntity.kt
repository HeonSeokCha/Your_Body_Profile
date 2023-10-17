package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity

@Entity(
    tableName = "taken_meal_info",
    primaryKeys = ["takenDate", "takenMealType"]
)
data class TakenMealInfoEntity(
    val takenDate: Long,
    val takenMealType: Int,
    val takenTime: Long,
)
