package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.FoodInfoEntity
import com.chs.your_body_profile.data.source.db.entity.MealHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MealHistoryDao : BaseDao<MealHistoryEntity> {

    @Query(
        "SELECT food.* " +
          "FROM meal_history as mealHistory " +
         "INNER JOIN food_info as food ON food.foodCode = mealHistory.foodCode " +
         "WHERE DATE(mealHistory.insertTime / 1000, 'unixepoch', 'localtime') = DATE(:targetDate / 1000, 'unixepoch', 'localtime') " +
         "ORDER BY mealHistory.insertTime DESC LIMIT 1"
    )
    abstract fun getDayLastInfo(targetDate: Long): Flow<FoodInfoEntity?>

    @Query(
        "SELECT SUM(foodInfo.calorie) " +
          "FROM meal_history as mealHistory " +
         "INNER JOIN food_info as foodInfo ON foodInfo.foodCode = mealHistory.foodCode " +
         "WHERE DATE(mealHistory.insertTime / 1000, 'unixepoch', 'localtime') = DATE(:targetDate / 1000, 'unixepoch', 'localtime') " +
         "ORDER BY mealHistory.insertTime, mealHistory.takenMealType "
    )
    abstract suspend fun getDayTotalCalorie(
        targetDate: Long
    ): Float

    @Query(
        "SELECT * " +
          "FROM meal_history as mealHistory " +
         "INNER JOIN food_info as foodInfo ON foodInfo.foodCode = mealHistory.foodCode " +
         "WHERE DATE(mealHistory.insertTime / 1000, 'unixepoch', 'localtime') = DATE(:targetDate / 1000, 'unixepoch', 'localtime') " +
         "ORDER BY mealHistory.insertTime, mealHistory.takenMealType "
    )
    abstract fun getDayMealHistoryFoodInfo(
        targetDate: Long
    ): Flow<Map<MealHistoryEntity, List<FoodInfoEntity>>>

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