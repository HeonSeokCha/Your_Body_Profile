package com.chs.your_body_profile.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFoodDetailInfo(
    @SerialName("DESC_KOR")
    val name: String,
    @SerialName("SERVING_WT")
    val servingWeight: Float,
    @SerialName("NUTR_COUNT1")
    val calorie: Int,
    @SerialName("NUTR_COUNT2")
    val carbohydrate: Float,
    @SerialName("NUTR_COUNT3")
    val protein: Float,
    @SerialName("NUTR_COUNT4")
    val fat: Float,
    @SerialName("NUTR_COUNT5")
    val sugar: Float,
    @SerialName("NUTR_COUNT6")
    val sodium: Float,
    @SerialName("NUTR_COUNT7")
    val cholesterol: Float,
    @SerialName("NUTR_COUNT8")
    val saturatedFat: Float,
    @SerialName("NUTR_COUNT9")
    val transFat: Float
)
