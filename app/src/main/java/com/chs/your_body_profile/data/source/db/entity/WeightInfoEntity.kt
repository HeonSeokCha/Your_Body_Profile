package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight_info")
data class WeightInfoEntity(
    @PrimaryKey
    val measureDateTime: Long,
    val weight: Float,
    val memo: String
)
