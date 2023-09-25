package com.chs.your_body_profile.domain.model

data class FoodDetailInfo(
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
    val transFat: Double = 0.0
)