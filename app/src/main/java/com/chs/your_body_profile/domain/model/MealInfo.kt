package com.chs.your_body_profile.domain.model

data class MealInfo(
    val beforeMealBloodSugar: Int,
    val foodList: List<FoodInfo>,
    val afterMealBloodSugar: Int,
    val mealType: MealType
)
