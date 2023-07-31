package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_info")
data class FoodInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val idx: Int = 0,
    val insertTime: Long = System.currentTimeMillis(),
    val type: String,
    val foodId: Int,
    val lastModified: Long
)