package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.FoodInfoEntity
import com.chs.your_body_profile.data.model.entity.MealHistoryEntity
import com.chs.your_body_profile.data.model.entity.MealHistoryWithFood
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MealHistoryWithFoodDao : BaseDao<MealHistoryWithFood> {

    @Query(
        "SELECT DISTINCT food.* " +
                "FROM meal_history AS mealHistory " +
                "INNER JOIN food_info AS food ON food.mealHistoryId = mealHistory.id " +
                "ORDER BY mealHistory.takenDate DESC LIMIT 10"
    )
    abstract suspend fun getRecentTakenFood():List<FoodInfoEntity>

    @Query(
        "SELECT * " +
                "FROM meal_history AS mealHistory " +
                "INNER JOIN food_info AS food ON food.mealHistoryId = mealHistory.id " +
                "WHERE mealHistory.takenDate = :time " +
                "ORDER BY mealHistory.takenMealType ASC"
    )
    abstract fun getDayTakenList(time: Long): Flow<Map<MealHistoryEntity, List<FoodInfoEntity>>>

    @Query(
        "SELECT * " +
                "FROM meal_history AS mealHistory " +
                "INNER JOIN food_info AS food ON food.mealHistoryId = mealHistory.id " +
                "WHERE mealHistory.takenDate = :takenDate " +
                "AND mealHistory.takenMealType = :mealTYpe "
    )
    abstract fun getDayMealTypeTakenList(
        takenDate: Long,
        mealTYpe: Int
    ): Flow<Map<MealHistoryEntity, List<FoodInfoEntity>>>

    @Query(
        "SELECT IFNULL(sum(food.calorie), 0) " +
                "FROM meal_history AS mealHistory " +
                "INNER JOIN food_info AS food ON food.mealHistoryId = mealHistory.id " +
                "WHERE mealHistory.takenDate = :time"
    )
    abstract fun getDayTakenTotalCalorie(time: Long): Flow<Float>

    @Query(
        "SELECT IFNULL(SUM(food.calorie), 0) " +
                "FROM meal_history AS mealHistory " +
                "INNER JOIN food_info AS food ON food.mealHistoryId = mealHistory.id " +
                "WHERE mealHistory.takenDate = :time " +
                "AND mealHistory.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalCalorie(
        time: Long,
        mealType: Int
    ): Flow<Int>

    @Query(
        "SELECT IFNULL(SUM(food.carbohydrate), 0) " +
                "FROM meal_history AS mealHistory " +
                "INNER JOIN food_info AS food ON food.mealHistoryId = mealHistory.id " +
                "WHERE mealHistory.takenDate = :time " +
                "AND mealHistory.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalCarbohydrate(
        time: Long,
        mealType: Int
    ): Flow<Float>

    @Query(
        "SELECT IFNULL(SUM(food.protein), 0) " +
                "FROM meal_history AS mealHistory " +
                "INNER JOIN food_info AS food ON food.mealHistoryId = mealHistory.id " +
                "WHERE mealHistory.takenDate = :time " +
                "AND mealHistory.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalProtein(
        time: Long,
        mealType: Int
    ): Flow<Float>

    @Query(
        "SELECT IFNULL(SUM(food.fat), 0) " +
                "FROM meal_history AS mealHistory " +
                "INNER JOIN food_info AS food ON food.mealHistoryId = mealHistory.id " +
                "WHERE mealHistory.takenDate = :time " +
                "AND mealHistory.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalFat(
        time: Long,
        mealType: Int
    ): Flow<Float>

    @Query(
        "SELECT takenDate," +
                "SUM(food.calorie) AS calorie " +
                "FROM meal_history " +
                "INNER JOIN food_info AS food ON food.mealHistoryId = id " +
                "WHERE takenDate BETWEEN :startDate AND :endDate " +
                "GROUP BY takenDate"
    )
    abstract suspend fun getPagingDayInfo(
        startDate: Long,
        endDate: Long
    ): Map<@MapColumn("takenDate") Long, @MapColumn("calorie") Float>
}