package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chs.your_body_profile.common.toLocalDateToMillis

@Entity(
    tableName = "drink_info",
    primaryKeys = ["takenDate", "drinkType"]
)
data class DrinkInfoEntity(
    val takenDate: Long,
    val drinkType: Int,
    val totalCups: Int,
)
