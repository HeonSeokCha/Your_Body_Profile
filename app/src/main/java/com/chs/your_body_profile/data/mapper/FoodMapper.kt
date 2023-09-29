package com.chs.your_body_profile.data.mapper

import com.chs.your_body_profile.common.toLocalDate
import com.chs.your_body_profile.common.toLocalDateTime
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.model.dto.ResponseFoodDetailInfo
import com.chs.your_body_profile.data.model.entity.FoodInfoEntity
import com.chs.your_body_profile.data.model.entity.TakenMealHistoryEntity
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.model.MealType

fun ResponseFoodDetailInfo.toFoodDetailInfo(): FoodDetailInfo {
    return FoodDetailInfo(
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

fun FoodDetailInfo.toFoodInfoEntity(mealHistoryInfo: MealHistoryInfo): FoodInfoEntity {
    return FoodInfoEntity(
        name = this.name,
        servingWeight = this.servingWeight,
        takenDate = mealHistoryInfo.takenDate.toMillis(),
        takenTime = mealHistoryInfo.takenDate.toMillis(),
        takenMealType = mealHistoryInfo.mealType.mean.first,
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

fun TakenMealHistoryEntity.toMealHistoryInfo(): MealHistoryInfo {
    return MealHistoryInfo(
        takenDate = this.takenDate.toLocalDate(),
        takenTime = this.takenTime.toLocalDateTime(),
        mealType = MealType.entries.find { it.mean.first == this.takenMealType } ?: MealType.UNKNOWN
    )
}

fun MealHistoryInfo.toTakenMealHistoryEntity(): TakenMealHistoryEntity {
    return TakenMealHistoryEntity(
        takenDate = this.takenDate.toMillis(),
        takenTime = this.takenDate.toMillis(),
        takenMealType = this.mealType.mean.first
    )
}