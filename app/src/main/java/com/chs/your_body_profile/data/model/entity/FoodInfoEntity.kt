package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chs.your_body_profile.common.toLocalDateToMillis

@Entity(
    tableName = "food_info",
    primaryKeys = ["insertTime", "type", "name"]
)
data class FoodInfoEntity(
    val insertTime: Long = System.currentTimeMillis().toLocalDateToMillis(),
    val type: String,
    val name: String,
    val servingWeight: Double = 0.0,
    val calorie: Double = 0.0,
    val carbohydrate: Double = 0.0,
    val fat: Double = 0.0,
    val protein: Double = 0.0,
    val sodium: Double = 0.0,
    val sugar: Double = 0.0,
    val cholesterol: Double = 0.0,
    val saturatedFat: Double = 0.0,
    val transFat: Double = 0.0,
    val lastModified: Long
)