package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.FoodInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FoodDao : BaseDao<FoodInfoEntity> {
    @Query(
        "SELECT IFNULL(SUM(calorie), 0) " +
          "FROM food_info " +
         "WHERE takenDate = :time"
    )
    abstract fun getDayTotalCalories(time: Long): Flow<Int>

    @Query(
        "SELECT * " +
          "FROM food_info " +
         "WHERE takenDate BETWEEN :startDate AND :endDate"
    )
    abstract suspend fun getPagingDayInfo(
        startDate: Long,
        endDate: Long
    ): List<FoodInfoEntity>

    @Query(
        "SELECT * " +
          "FROM food_info " +
         "ORDER BY takenTime DESC " +
         "LIMIT 10"
    )
    abstract suspend fun getRecentTakenFood(): List<FoodInfoEntity>
}