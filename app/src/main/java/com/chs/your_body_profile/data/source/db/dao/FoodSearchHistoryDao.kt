package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.FoodSearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FoodSearchHistoryDao : BaseDao<FoodSearchHistoryEntity> {

    @Query("SELECT foodName FROM food_search_history ORDER BY insertDate DESC LIMIT 10")
    abstract fun getRecentSearchHistory(): Flow<List<String>>
}