package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity

@Entity(
    tableName = "drink_info",
    primaryKeys = ["takenDate", "drinkType"]
)
data class DrinkInfoEntity(
    val takenDate: Long,
    val drinkType: String,
    val totalCups: Int,
)
