package com.chs.your_body_profile.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFoodInfoBody(
    @SerialName("pageNo")
    val pageNo: Int,
    @SerialName("totalCount")
    val totalCount: Int,
    @SerialName("numOfRows")
    val numOfRows: Int,
    @SerialName("items")
    val items: List<ResponseFoodDetailInfo>
)