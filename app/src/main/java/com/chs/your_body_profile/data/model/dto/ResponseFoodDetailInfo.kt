package com.chs.your_body_profile.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFoodDetailInfo(
    @SerialName("DESC_KOR")
    val name: String,
    @SerialName("SERVING_SIZE")
    val servingWeight: Int = 0,
    @SerialName("NUTR_CONT1")
    val calorie: Int = 0,
    @Serializable(with = FoodSerializer::class)
    @SerialName("NUTR_CONT2")
    val carbohydrate: Float = 0f,
    @Serializable(with = FoodSerializer::class)
    @SerialName("NUTR_CONT3")
    val protein: Float = 0f,
    @Serializable(with = FoodSerializer::class)
    @SerialName("NUTR_CONT4")
    val fat: Float = 0f,
    @Serializable(with = FoodSerializer::class)
    @SerialName("NUTR_CONT5")
    val sugar: Float = 0f,
    @Serializable(with = FoodSerializer::class)
    @SerialName("NUTR_CONT6")
    val sodium: Float = 0f,
    @Serializable(with = FoodSerializer::class)
    @SerialName("NUTR_CONT7")
    val cholesterol: Float = 0f,
    @Serializable(with = FoodSerializer::class)
    @SerialName("NUTR_CONT8")
    val saturatedFat: Float = 0f,
    @Serializable(with = FoodSerializer::class)
    @SerialName("NUTR_CONT9")
    val transFat: Float = 0f
)
