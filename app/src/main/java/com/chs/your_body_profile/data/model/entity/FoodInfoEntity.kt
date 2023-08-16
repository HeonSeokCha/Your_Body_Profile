package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chs.your_body_profile.common.toLocalDateToMillis

@Entity(
    tableName = "food_info",
    primaryKeys = ["insertTime", "name"]
)
data class FoodInfoEntity(
    val insertTime: Long = System.currentTimeMillis().toLocalDateToMillis(),
    val name: String,
    val calorie: Int,
    val type: String,
    val lastModified: Long
)