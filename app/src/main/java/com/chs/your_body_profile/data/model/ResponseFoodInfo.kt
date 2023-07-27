package com.chs.your_body_profile.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFoodInfo(
    @SerialName("header")
    val header: ResponseFoodInfoHeader,
    @SerialName("body")
    val body: ResponseFoodInfoBody
)
