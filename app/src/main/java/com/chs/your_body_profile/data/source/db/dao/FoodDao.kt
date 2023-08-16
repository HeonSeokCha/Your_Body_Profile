package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.FoodInfoEntity
import com.chs.your_body_profile.domain.model.FoodInfo
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FoodDao : BaseDao<FoodInfoEntity> {

    @Query("""
        SELECT *
          FROM food_info
         WHERE insertTime = :time
         ORDER BY lastModified DESC
         LIMIT 1
    """)
    abstract fun getDayLastFoodInfo(time: Long): Flow<FoodInfoEntity?>

    @Query("""
        SELECT IFNULL(SUM(calorie), 0)
          FROM food_info
         WHERE insertTime = :time
    """)
    abstract fun getDayTotalCalorie(time: Long): Flow<Int>
}