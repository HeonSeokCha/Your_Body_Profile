package com.chs.your_body_profile.data.source.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFoodResult(
    @SerialName("CODE")
    val code: String,
    @SerialName("MSG")
    val message: String
)
