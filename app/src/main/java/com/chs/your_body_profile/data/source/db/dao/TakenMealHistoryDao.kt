package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.MapInfo
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.FoodInfoEntity
import com.chs.your_body_profile.data.model.entity.TakenMealHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TakenMealHistoryDao : BaseDao<TakenMealHistoryEntity> {

    @Query(
        "DELETE " +
          "FROM taken_meal_history " +
         "WHERE takenDate = :takenDate " +
           "AND takenTime = :takenTime " +
           "AND takenMealType = :mealType"
    )
    abstract suspend fun deleteMealHistory(
        takenDate: Long,
        takenTime: Long,
        mealType: Int
    )

    @Query(
        "SELECT * " +
          "FROM taken_meal_history AS mealHistory " +
         "INNER JOIN food_info AS food ON food.foodCode = mealHistory.foodCode " +
         "ORDER BY mealHistory.takenTime DESC LIMIT 10"
    )
    abstract suspend fun getRecentTakenFood():List<FoodInfoEntity>

    @Query(
        "SELECT * " +
          "FROM taken_meal_history AS mealHistory " +
         "INNER JOIN food_info AS food ON food.foodCode = mealHistory.foodCode " +
         "WHERE mealHistory.takenDate = :time " +
         "ORDER BY mealHistory.takenMealType, mealHistory.takenTime ASC"
    )
    abstract fun getDayTakenList(time: Long): Flow<Map<TakenMealHistoryEntity, List<FoodInfoEntity>>>

    @Query(
        "SELECT * " +
          "FROM taken_meal_history AS mealHistory " +
         "INNER JOIN food_info AS food ON food.foodCode = mealHistory.foodCode " +
         "WHERE mealHistory.takenDate = :time " +
           "AND mealHistory.takenMealType = :mealTYpe " +
         "ORDER BY mealHistory.takenTime ASC"
    )
    abstract fun getDayMealTypeTakenList(
        time: Long,
        mealTYpe: String
    ): Flow<Map<TakenMealHistoryEntity, List<FoodInfoEntity>>>

    @Query(
        "SELECT IFNULL(sum(food.calorie), 0) " +
          "FROM taken_meal_history AS mealHistory " +
         "INNER JOIN food_info AS food ON food.foodCode = mealHistory.foodCode " +
         "WHERE mealHistory.takenDate = :time"
    )
    abstract fun getDayTakenTotalCalorie(time: Long): Flow<Float>

    @Query(
        "SELECT IFNULL(SUM(food.calorie), 0) " +
          "FROM taken_meal_history AS mealHistory " +
         "INNER JOIN food_info AS food ON food.foodCode = mealHistory.foodCode " +
         "WHERE mealHistory.takenDate = :time " +
           "AND mealHistory.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalCalorie(
        time: Long,
        mealType: Int
    ): Flow<Int>

    @Query(
        "SELECT IFNULL(SUM(food.carbohydrate), 0) " +
          "FROM taken_meal_history AS mealHistory " +
         "INNER JOIN food_info AS food ON food.foodCode = mealHistory.foodCode " +
         "WHERE mealHistory.takenDate = :time " +
           "AND mealHistory.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalCarbohydrate(
        time: Long,
        mealType: Int
    ): Flow<Float>

    @Query(
        "SELECT IFNULL(SUM(food.protein), 0) " +
          "FROM taken_meal_history AS mealHistory " +
         "INNER JOIN food_info AS food ON food.foodCode = mealHistory.foodCode " +
         "WHERE mealHistory.takenDate = :time " +
           "AND mealHistory.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalProtein(
        time: Long,
        mealType: Int
    ): Flow<Float>

    @Query(
        "SELECT IFNULL(SUM(food.fat), 0) " +
          "FROM taken_meal_history AS mealHistory " +
         "INNER JOIN food_info AS food ON food.foodCode = mealHistory.foodCode " +
         "WHERE mealHistory.takenDate = :time " +
           "AND mealHistory.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalFat(
        time: Long,
        mealType: Int
    ): Flow<Float>

    @MapInfo(keyColumn = "takenDate", valueColumn = "calorie")
    @Query(
        "SELECT taken_meal_history.takenDate AS takenDate," +
               "SUM(food.calorie) AS calorie " +
          "FROM taken_meal_history " +
         "INNER JOIN food_info as food " +
            "ON taken_meal_history.foodCode = food.foodCode " +
         "WHERE takenDate BETWEEN :startDate AND :endDate"
    )
    abstract fun getPagingDayInfo(
        startDate: Long,
        endDate: Long
    ): Map<Long, Float>
}