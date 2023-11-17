package com.chs.your_body_profile.data.mapper

import com.chs.your_body_profile.data.model.dto.ResponseFoodDetailInfo
import com.chs.your_body_profile.data.model.entity.FoodInfoEntity
import com.chs.your_body_profile.domain.model.FoodDetailInfo

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

fun FoodDetailInfo.toFoodInfoEntity(historyId: Long): FoodInfoEntity {
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
        mealHistoryId = historyId
    )
}