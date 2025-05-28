package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drink_info")
data class DrinkInfoEntity(
    @PrimaryKey
    val takenDateTime: Long,
    val drinkType: String
)
