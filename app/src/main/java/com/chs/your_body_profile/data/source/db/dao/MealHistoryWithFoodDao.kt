package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.FoodInfoEntity
import com.chs.your_body_profile.data.source.db.entity.MealHistoryEntity
import com.chs.your_body_profile.data.source.db.entity.MealHistoryWithFood
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MealHistoryWithFoodDao : BaseDao<MealHistoryWithFood> {

    @Query(
        "SELECT foodInfo.* " +
          "FROM meal_history_with_food AS mealHistoryWithFood " +
         "INNER JOIN food_info AS foodInfo ON mealHistoryWithFood.foodCode = foodInfo.foodCode " +
         "ORDER BY mealHistoryWithFood.takenDate, mealHistoryWithFood.takenMealType DESC LIMIT 10"
    )
    abstract suspend fun getRecentTakenFood():List<FoodInfoEntity>

    @Query(
        "SELECT mealHistory.*, foodInfo.* " +
          "FROM meal_history_with_food AS mealHistoryWithFood " +
         "INNER JOIN meal_history AS mealHistory ON mealHistory.takenDate = mealHistoryWithFood.takenDate " +
           "AND mealHistory.takenMealType = mealHistoryWithFood.takenMealType " +
         "INNER JOIN food_info AS foodInfo ON mealHistoryWithFood.foodCode = foodInfo.foodCode " +
         "WHERE mealHistoryWithFood.takenDate = :time " +
         "ORDER BY mealHistoryWithFood.takenMealType ASC"
    )
    abstract fun getDayTakenList(time: Long): Flow<Map<MealHistoryEntity, List<FoodInfoEntity>>>

    @Query(
        "SELECT mealHistory.*, foodInfo.* " +
          "FROM meal_history_with_food AS mealHistoryWithFood " +
         "INNER JOIN meal_history AS mealHistory ON mealHistory.takenDate = mealHistoryWithFood.takenDate " +
           "AND mealHistory.takenMealType = mealHistoryWithFood.takenMealType " +
         "INNER JOIN food_info AS foodInfo ON mealHistoryWithFood.foodCode = foodInfo.foodCode " +
         "WHERE mealHistoryWithFood.takenDate = :takenDate " +
           "AND mealHistoryWithFood.takenMealType = :mealTYpe "
    )
    abstract fun getDayMealTypeTakenList(
        takenDate: Long,
        mealTYpe: Int
    ): Flow<Map<MealHistoryEntity, List<FoodInfoEntity>>>

    @Query(
        "SELECT IFNULL(sum(foodInfo.calorie), 0) " +
          "FROM meal_history_with_food AS mealHistoryWithFood " +
         "INNER JOIN food_info AS foodInfo ON mealHistoryWithFood.foodCode = foodInfo.foodCode " +
         "WHERE mealHistoryWithFood.takenDate = :time"
    )
    abstract fun getDayTakenTotalCalorie(time: Long): Flow<Float>

    @Query(
        "SELECT IFNULL(SUM(foodInfo.calorie), 0) " +
          "FROM meal_history_with_food AS mealHistoryWithFood " +
         "INNER JOIN food_info AS foodInfo ON mealHistoryWithFood.foodCode = foodInfo.foodCode " +
         "WHERE mealHistoryWithFood.takenDate = :time " +
           "AND mealHistoryWithFood.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalCalorie(
        time: Long,
        mealType: Int
    ): Flow<Int>

    @Query(
        "SELECT IFNULL(SUM(foodInfo.carbohydrate), 0) " +
          "FROM meal_history_with_food AS mealHistoryWithFood " +
         "INNER JOIN food_info AS foodInfo ON mealHistoryWithFood.foodCode = foodInfo.foodCode " +
         "WHERE mealHistoryWithFood.takenDate = :time " +
           "AND mealHistoryWithFood.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalCarbohydrate(
        time: Long,
        mealType: Int
    ): Flow<Float>

    @Query(
        "SELECT IFNULL(SUM(foodInfo.protein), 0) " +
          "FROM meal_history_with_food AS mealHistoryWithFood " +
         "INNER JOIN food_info AS foodInfo ON mealHistoryWithFood.foodCode = foodInfo.foodCode " +
         "WHERE mealHistoryWithFood.takenDate = :time " +
           "AND mealHistoryWithFood.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalProtein(
        time: Long,
        mealType: Int
    ): Flow<Float>

    @Query(
        "SELECT IFNULL(SUM(foodInfo.fat), 0) " +
          "FROM meal_history_with_food AS mealHistoryWithFood " +
         "INNER JOIN food_info AS foodInfo ON mealHistoryWithFood.foodCode = foodInfo.foodCode " +
         "WHERE mealHistoryWithFood.takenDate = :time " +
           "AND mealHistoryWithFood.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalFat(
        time: Long,
        mealType: Int
    ): Flow<Float>

    @Query(
        "SELECT mealHistoryWithFood.takenDate," +
               "SUM(foodInfo.calorie) AS calorie " +
          "FROM meal_history_with_food AS mealHistoryWithFood " +
         "INNER JOIN food_info AS foodInfo ON mealHistoryWithFood.foodCode = foodInfo.foodCode " +
         "WHERE takenDate BETWEEN :startDate AND :endDate " +
         "GROUP BY takenDate"
    )
    abstract suspend fun getPagingDayInfo(
        startDate: Long,
        endDate: Long
    ): Map<@MapColumn("takenDate") Long, @MapColumn("calorie") Float>
}