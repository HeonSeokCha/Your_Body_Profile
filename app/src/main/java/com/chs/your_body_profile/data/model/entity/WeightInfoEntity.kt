package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chs.your_body_profile.common.toLocalDateToMillis

@Entity(
    tableName = "weight_info",
    primaryKeys = ["measureDate", "measureTime"]
)
data class WeightInfoEntity(
    val measureDate: Long,
    val measureTime: Long,
    val weight: Float,
    val memo: String
)
