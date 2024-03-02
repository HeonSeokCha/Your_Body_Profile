package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "meal_history",
    primaryKeys = ["insertTime, foodCode"]
)
data class MealHistoryEntity(
    val insertTime: Long,
    val foodCode: String,
    val takenMealType: Int
)