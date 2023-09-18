package com.chs.your_body_profile.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFoodDetailInfo(
    @SerialName("DESC_KOR")
    val name: String,
    @Serializable(with = DoubleSerializer::class)
    @SerialName("SERVING_SIZE")
    val servingWeight: Double = 0.0,
    @Serializable(with = DoubleSerializer::class)
    @SerialName("NUTR_CONT1")
    val calorie: Double = 0.0,
    @Serializable(with = DoubleSerializer::class)
    @SerialName("NUTR_CONT2")
    val carbohydrate: Double = 0.0,
    @Serializable(with = DoubleSerializer::class)
    @SerialName("NUTR_CONT3")
    val protein: Double = 0.0,
    @Serializable(with = DoubleSerializer::class)
    @SerialName("NUTR_CONT4")
    val fat: Double = 0.0,
    @Serializable(with = DoubleSerializer::class)
    @SerialName("NUTR_CONT5")
    val sugar: Double = 0.0,
    @Serializable(with = DoubleSerializer::class)
    @SerialName("NUTR_CONT6")
    val sodium: Double = 0.0,
    @Serializable(with = DoubleSerializer::class)
    @SerialName("NUTR_CONT7")
    val cholesterol: Double = 0.0,
    @Serializable(with = DoubleSerializer::class)
    @SerialName("NUTR_CONT8")
    val saturatedFat: Double = 0.0,
    @Serializable(with = DoubleSerializer::class)
    @SerialName("NUTR_CONT9")
    val transFat: Double = 0.0
)
