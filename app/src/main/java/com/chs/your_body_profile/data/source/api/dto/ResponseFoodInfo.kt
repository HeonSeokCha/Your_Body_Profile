package com.chs.your_body_profile.data.source.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFoodInfo(
    @SerialName("I2790")
    val foodService: ResponseFoodService
)
