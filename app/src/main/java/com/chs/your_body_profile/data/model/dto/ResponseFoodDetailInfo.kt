package com.chs.your_body_profile.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFoodDetailInfo(
    @SerialName("DESC_KOR")
    val name: String,
    @SerialName("SERVING_WT")
    val servingWeight: Double,
    @SerialName("NUTR_CONT1")
    val calorie: Double,
    @SerialName("NUTR_CONT2")
    val carbohydrate: Double,
    @SerialName("NUTR_CONT3")
    val protein: Double,
    @SerialName("NUTR_CONT4")
    val fat: Double,
    @SerialName("NUTR_CONT5")
    val sugar: Double,
    @SerialName("NUTR_CONT6")
    val sodium: Double,
    @SerialName("NUTR_CONT7")
    val cholesterol: Double,
    @SerialName("NUTR_CONT8")
    val saturatedFat: Double,
    @SerialName("NUTR_CONT9")
    val transFat: Double
)
