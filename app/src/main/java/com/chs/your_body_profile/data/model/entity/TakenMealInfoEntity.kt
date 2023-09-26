package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity

@Entity(
    tableName = "taken_meal"
)
data class TakenMealInfoEntity(
    val mealTime: Long,
    val mealType: String,
    val mealId: Int,
    val lastModified: Long
)
