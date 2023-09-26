package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity

@Entity(
    "nutritional",
    primaryKeys = ["name", "servingWeight"]
)
data class NutritionalInfoEntity(
    val name: String,
    val servingWeight: Int,
    val calorie: Float = 0f,
    val carbohydrate: Float = 0f,
    val fat: Float = 0f,
    val protein: Float = 0f,
    val sodium: Float = 0f,
    val sugar: Float = 0f,
    val cholesterol: Float = 0f,
    val saturatedFat: Float = 0f,
    val transFat: Float = 0f,
)
