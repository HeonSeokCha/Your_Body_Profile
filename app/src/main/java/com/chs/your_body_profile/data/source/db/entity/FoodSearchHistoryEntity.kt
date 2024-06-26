package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_search_history")
data class FoodSearchHistoryEntity(
    @PrimaryKey
    val foodName: String,
    val insertDate: Long = System.currentTimeMillis()
)
