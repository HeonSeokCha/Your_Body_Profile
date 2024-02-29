package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "meal_history_with_food"
)
data class MealHistoryWithFood(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val takenDate: Long,
    val takenMealType: Int,
    val foodCode: String
)
