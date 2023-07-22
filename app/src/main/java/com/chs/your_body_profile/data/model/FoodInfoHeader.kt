package com.chs.your_body_profile.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodInfoHeader(
    @SerialName("resultCode")
    val resultCode: String,
    @SerialName("resultMsg")
    val resultMsg: String
)
