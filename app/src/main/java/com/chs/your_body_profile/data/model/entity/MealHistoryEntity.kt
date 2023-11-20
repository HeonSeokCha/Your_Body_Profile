package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_history")
data class MealHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val takenDate: Long,
    val takenTime: Long,
    val takenMealType: Int
)