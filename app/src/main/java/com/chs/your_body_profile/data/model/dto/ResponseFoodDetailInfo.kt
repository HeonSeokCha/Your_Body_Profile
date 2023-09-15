package com.chs.your_body_profile.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFoodDetailInfo(
    @SerialName("DESC_KOR")
    val name: String,
    @SerialName("SERVING_SIZE")
    val servingWeight: String,
    @SerialName("NUTR_CONT1")
    val calorie: String,
    @SerialName("NUTR_CONT2")
    val carbohydrate: String,
    @SerialName("NUTR_CONT3")
    val protein: String,
    @SerialName("NUTR_CONT4")
    val fat: String,
    @SerialName("NUTR_CONT5")
    val sugar: String,
    @SerialName("NUTR_CONT6")
    val sodium: String,
    @SerialName("NUTR_CONT7")
    val cholesterol: String,
    @SerialName("NUTR_CONT8")
    val saturatedFat: String,
    @SerialName("NUTR_CONT9")
    val transFat: String
)
