package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drink_info")
data class DrinkInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val idx: Int = 0,
    val insertTime: Long = System.currentTimeMillis(),
    val drinkType: String,
    val totalCups: Int,
    val lastModified: Long
)
