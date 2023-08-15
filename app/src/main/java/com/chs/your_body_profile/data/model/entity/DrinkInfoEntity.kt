package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "drink_info",
    primaryKeys = ["insertDate", "drinkType"]
)
data class DrinkInfoEntity(
    val insertDate: Long,
    val drinkType: String,
    val totalCups: Int,
    val lastModified: Long
)
