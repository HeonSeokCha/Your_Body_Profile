package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "meal_info"
)
data class MealInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val takenIdx: Long = 0L,
    val bloodSugarMeasureTime: Long,
    val mealType: Int,
    val mealName: String
)