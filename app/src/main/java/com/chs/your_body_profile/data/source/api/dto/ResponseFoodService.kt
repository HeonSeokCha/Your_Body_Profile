package com.chs.your_body_profile.data.source.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFoodService(
    @SerialName("total_count")
    val totalCount: Int,
    @SerialName("row")
    val row: List<ResponseFoodDetailInfo> = emptyList(),
    @SerialName("RESULT")
    val result: ResponseFoodResult
)
