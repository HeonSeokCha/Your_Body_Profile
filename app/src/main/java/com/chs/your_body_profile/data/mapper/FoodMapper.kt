package com.chs.your_body_profile.data.mapper

import com.chs.your_body_profile.common.toLocalDate
import com.chs.your_body_profile.common.toLocalDateTime
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.model.dto.ResponseFoodDetailInfo
import com.chs.your_body_profile.data.model.entity.FoodInfoEntity
import com.chs.your_body_profile.data.model.entity.MealHistoryEntity
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.model.MealHistoryInfo

fun ResponseFoodDetailInfo.toFoodDetailInfo(): FoodDetailInfo {
    return FoodDetailInfo(
        code = this.foodCode,
        name = this.name,
        servingWeight = this.servingWeight,
        calorie = this.calorie,
        carbohydrate = this.carbohydrate,
        protein = this.protein,
        fat = this.fat,
        sugar = this.sugar,
        sodium = this.sodium,
        cholesterol = this.cholesterol,
        saturatedFat = this.saturatedFat,
        transFat = this.transFat
    )
}

fun FoodDetailInfo.toResponseFoodDetailInfo(): ResponseFoodDetailInfo {
    return ResponseFoodDetailInfo(
        foodCode = this.code,
        name = this.name,
        servingWeight = this.servingWeight,
        calorie = this.calorie,
        carbohydrate = this.carbohydrate,
        protein = this.protein,
        fat = this.fat,
        sugar = this.sugar,
        sodium = this.sodium,
        cholesterol = this.cholesterol,
        saturatedFat = this.saturatedFat,
        transFat = this.transFat
    )
}

fun FoodInfoEntity.toFoodDetailInfo(): FoodDetailInfo {
    return FoodDetailInfo(
        code = this.foodCode,
        name = this.name,
        servingWeight = this.servingWeight,
        calorie = this.calorie,
        carbohydrate = this.carbohydrate,
        protein = this.protein,
        fat = this.fat,
        sugar = this.sugar,
        sodium = this.sodium,
        cholesterol = this.cholesterol,
        saturatedFat = this.saturatedFat,
        transFat = this.transFat
    )
}

fun FoodDetailInfo.toFoodInfoEntity(): FoodInfoEntity {
    return FoodInfoEntity(
        foodCode = this.code,
        name = this.name,
        servingWeight = this.servingWeight,
        calorie = this.calorie,
        carbohydrate = this.carbohydrate,
        protein = this.protein,
        fat = this.fat,
        sugar = this.sugar,
        sodium = this.sodium,
        cholesterol = this.cholesterol,
        saturatedFat = this.saturatedFat,
        transFat = this.transFat,
    )
}

fun MealHistoryInfo.toEntity(): MealHistoryEntity {
    return MealHistoryEntity(
        takenDate = this.takenDate.toMillis(),
        takenTime = this.takenTime.toMillis(),
        takenMealType = this.mealType.mean.first
    )
}

fun MealHistoryEntity.toTakenMealHistoryInfo(): MealHistoryInfo {
    return MealHistoryInfo(
        takenDate = this.takenDate.toLocalDate(),
        takenTime = this.takenTime.toLocalDateTime(),
        mealType = MealType.values().find { it.mean.first == this.takenMealType }
            ?: MealType.MORNING
    )
}