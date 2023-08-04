package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drink_info")
data class DrinkInfoEntity(
    @PrimaryKey
    val insertDate: Long = 0L,
    val drinkType: String,
    val totalCups: Int,
    val lastModified: Long
)
