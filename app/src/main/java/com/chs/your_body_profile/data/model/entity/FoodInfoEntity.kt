package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chs.your_body_profile.common.toLocalDateToMillis

@Entity(
    tableName = "food_info",
    primaryKeys = ["name", "servingWeight"]
)
data class FoodInfoEntity(
    val name: String,
    val servingWeight: Int,
    val insertTime: Long = System.currentTimeMillis()
)