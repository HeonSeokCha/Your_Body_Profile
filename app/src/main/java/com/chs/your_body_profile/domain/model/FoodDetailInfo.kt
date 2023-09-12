package com.chs.your_body_profile.domain.model


data class FoodDetailInfo(
    val name: String,
    val servingWeight: Double,
    val calorie: Double,
    val carbohydrate: Double,
    val fat: Double,
    val protein: Double,
    val sodium: Double,
    val sugar: Double,
    val cholesterol: Double,
    val saturatedFat: Double,
    val transFat: Double
)
