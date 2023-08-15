package com.chs.your_body_profile.domain.model


data class FoodDetailInfo(
    val name: String,
    val servingWeight: Float,
    val calorie: Int,
    val carbohydrate: Float,
    val fat: Float,
    val protein: Float,
    val sodium: Float,
    val sugar: Float,
    val cholesterol: Float,
    val saturatedFat: Float,
    val transFat: Float
)
