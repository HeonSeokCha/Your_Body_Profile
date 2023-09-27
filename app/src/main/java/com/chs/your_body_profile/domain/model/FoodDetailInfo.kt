package com.chs.your_body_profile.domain.model

data class FoodDetailInfo(
    val name: String,
    val servingWeight: Int = 0,
    val calorie: Int = 0,
    val carbohydrate: Float = 0f,
    val fat: Float = 0f,
    val protein: Float = 0f,
    val sodium: Float = 0f,
    val sugar: Float = 0f,
    val cholesterol: Float = 0f,
    val saturatedFat: Float = 0f,
    val transFat: Float = 0f
)