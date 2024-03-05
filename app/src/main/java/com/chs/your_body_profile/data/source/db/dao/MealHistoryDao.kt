package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.FoodInfoEntity
import com.chs.your_body_profile.data.source.db.entity.MealHistoryEntity

@Dao
abstract class MealHistoryDao : BaseDao<MealHistoryEntity> {

    @Query(
        "SELECT * " +
          "FROM meal_history as mealHistory " +
         "INNER JOIN food_info as foodInfo ON foodInfo.foodCode = mealHistory.foodCode " +
         "WHERE DATE(mealHistory.insertTime / 1000, 'unixepoch', 'localtime') =  DATE(:targetDate / 1000, 'unixepoch', 'localtime') " +
         "ORDER BY mealHistory.insertTime, mealHistory.takenMealType "
    )
    abstract suspend fun getDayMealHistoryFoodInfo(
        targetDate: Long
    ): Map<MealHistoryEntity, List<FoodInfoEntity>>

    @Query(
        "SELECT * " +
          "FROM meal_history as mealHistory " +
         "INNER JOIN food_info as foodInfo ON foodInfo.foodCode = mealHistory.foodCode " +
         "WHERE DATE(mealHistory.insertTime / 1000, 'unixepoch', 'localtime') = DATE(:targetDate / 1000, 'unixepoch', 'localtime') " +
           "AND mealHistory.takenMealType = :mealType " +
         "ORDER BY mealHistory.insertTime DESC"
    )
    abstract suspend fun getDayMealHistoryMealTypeFoodInfo(
        targetDate: Long,
        mealType: Int
    ): Map<MealHistoryEntity, List<FoodInfoEntity>>

    @Query(
        "SELECT foodInfo.* " +
          "FROM meal_history as mealHistory " +
         "INNER JOIN food_info as foodInfo ON foodInfo.foodCode = mealHistory.foodCode " +
         "ORDER BY mealHistory.insertTime LIMIT 10 "
    )
    abstract suspend fun getRecentTakenFoodList(): List<FoodInfoEntity>
}