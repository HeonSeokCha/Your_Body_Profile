package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.FoodInfoEntity
import com.chs.your_body_profile.data.model.entity.TakenMealHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TakenMealHistoryDao : BaseDao<TakenMealHistoryEntity> {

    @Query(
        "SELECT * " +
          "FROM taken_meal_history AS mealHistory " +
         "INNER JOIN food_info AS food ON food.takenDate = mealHistory.takenDate " +
           "AND food.takenTime = mealHistory.takenTime " +
           "AND food.takenMealType = mealHistory.takenMealType " +
         "WHERE mealHistory.takenDate = :time"
    )
    abstract fun getDayTakenList(time: Long): Flow<Map<TakenMealHistoryEntity, List<FoodInfoEntity>>>

    @Query(
        "SELECT * " +
          "FROM taken_meal_history AS mealHistory " +
         "INNER JOIN food_info AS food ON food.takenDate = mealHistory.takenDate " +
           "AND food.takenTime = mealHistory.takenTime " +
           "AND food.takenMealType = mealHistory.takenMealType " +
         "WHERE mealHistory.takenDate = :time " +
           "AND mealHistory.takenMealType = :mealTYpe"
    )
    abstract fun getDayMealTypeTakenList(
        time: Long,
        mealTYpe: String
    ): Flow<Map<TakenMealHistoryEntity, List<FoodInfoEntity>>>

    @Query(
        "SELECT IFNULL(sum(food.calorie), 0) " +
          "FROM taken_meal_history AS mealHistory " +
         "INNER JOIN food_info AS food ON food.takenDate = mealHistory.takenDate " +
           "AND food.takenTime = mealHistory.takenTime " +
           "AND food.takenMealType = mealHistory.takenMealType " +
         "WHERE mealHistory.takenDate = :time"
    )
    abstract fun getDayTakenTotalCalorie(time: Long): Flow<Int>

    @Query(
        "SELECT IFNULL(SUM(food.calorie), 0) " +
          "FROM taken_meal_history AS mealHistory " +
         "INNER JOIN food_info AS food ON food.takenDate = mealHistory.takenDate " +
           "AND food.takenTime = mealHistory.takenTime " +
           "AND food.takenMealType = mealHistory.takenMealType " +
         "WHERE mealHistory.takenDate = :time " +
           "AND mealHistory.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalCalorie(
        time: Long,
        mealType: String
    ): Flow<Int>

    @Query(
        "SELECT IFNULL(SUM(food.carbohydrate), 0) " +
          "FROM taken_meal_history AS mealHistory " +
         "INNER JOIN food_info AS food ON food.takenDate = mealHistory.takenDate " +
           "AND food.takenTime = mealHistory.takenTime " +
           "AND food.takenMealType = mealHistory.takenMealType " +
         "WHERE mealHistory.takenDate = :time " +
           "AND mealHistory.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalCarbohydrate(
        time: Long,
        mealType: String
    ): Flow<Float>

    @Query(
        "SELECT IFNULL(SUM(food.protein), 0) " +
          "FROM taken_meal_history AS mealHistory " +
         "INNER JOIN food_info AS food ON food.takenDate = mealHistory.takenDate " +
           "AND food.takenTime = mealHistory.takenTime " +
           "AND food.takenMealType = mealHistory.takenMealType " +
         "WHERE mealHistory.takenDate = :time " +
           "AND mealHistory.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalProtein(
        time: Long,
        mealType: String
    ): Flow<Float>

    @Query(
        "SELECT IFNULL(SUM(food.fat), 0) " +
          "FROM taken_meal_history AS mealHistory " +
         "INNER JOIN food_info AS food ON food.takenDate = mealHistory.takenDate " +
           "AND food.takenTime = mealHistory.takenTime " +
           "AND food.takenMealType = mealHistory.takenMealType " +
         "WHERE mealHistory.takenDate = :time " +
           "AND mealHistory.takenMealType = :mealType"
    )
    abstract fun getDayMealTypeTotalFat(
        time: Long,
        mealType: String
    ): Flow<Float>

}