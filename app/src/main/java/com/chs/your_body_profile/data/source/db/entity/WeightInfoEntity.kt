package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity

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
