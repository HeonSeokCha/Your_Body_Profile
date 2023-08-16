package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chs.your_body_profile.common.toLocalDateToMillis

@Entity(
    tableName = "drink_info",
    primaryKeys = ["insertDate", "drinkType"]
)
data class DrinkInfoEntity(
    val insertDate: Long = System.currentTimeMillis().toLocalDateToMillis(),
    val drinkType: String,
    val totalCups: Int,
    val lastModifyTime: Long
)
