package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight_info")
data class WeightInfoEntity(
    @PrimaryKey
    val insertDate: Long = 0L,
    val measureTime: Long,
    val weight: Float,
    val memo: String,
    val lastModifyTime: Long
)
