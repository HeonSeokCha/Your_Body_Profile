package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_search_history")
data class FoodSearchHistoryEntity(
    @PrimaryKey
    val query: String,
    val insertDate: Long = System.currentTimeMillis()
)
