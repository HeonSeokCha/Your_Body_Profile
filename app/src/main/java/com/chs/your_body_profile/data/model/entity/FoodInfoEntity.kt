package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chs.your_body_profile.common.toLocalDateToMillis

@Entity(tableName = "food_info")
data class FoodInfoEntity(
    @PrimaryKey
    val foodCode: String,
    val name: String,
    val servingWeight: Float,
    val calorie: Float,
    val carbohydrate: Float = 0f,
    val fat: Float = 0f,
    val protein: Float = 0f,
    val sodium: Float = 0f,
    val sugar: Float = 0f,
    val cholesterol: Float = 0f,
    val saturatedFat: Float = 0f,
    val transFat: Float = 0f
)