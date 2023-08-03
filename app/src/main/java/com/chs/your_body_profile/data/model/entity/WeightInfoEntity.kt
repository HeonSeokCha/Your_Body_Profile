package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight_info")
data class WeightInfoEntity(
    @PrimaryKey
    val insertTime: Long = System.currentTimeMillis(),
    val weight: Float,
    val memo: String,
    val lastModifyTime: Long
)
