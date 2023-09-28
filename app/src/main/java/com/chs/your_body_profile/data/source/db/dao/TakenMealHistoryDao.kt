package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.FoodInfoEntity
import com.chs.your_body_profile.data.model.entity.TakenMealHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TakenMealHistoryDao : BaseDao<TakenMealHistoryEntity> {

    @Query("SELECT * FROM taken_meal_history as mealHistory " +
            "INNER JOIN food_info as food ON food.takenDate = mealHistory.takenDate" +
            "  AND food.takenTime = mealHistory.takenTime" +
            "  AND food.takenMealType = mealHistory.takenMealType " +
            "WHERE mealHistory.takenDate = :time")
    abstract fun getDayTakenMealList(time: Long): Map<TakenMealHistoryEntity, List<FoodInfoEntity>>

    @Query("SELECT sum(food.calorie) FROM taken_meal_history as mealHistory " +
            "INNER JOIN food_info as food ON food.takenDate = mealHistory.takenDate" +
            "  AND food.takenTime = mealHistory.takenTime" +
            "  AND food.takenMealType = mealHistory.takenMealType " +
            "WHERE mealHistory.takenDate = :time")
    abstract fun getDayTakenTotalCalorie(time: Long): Map<TakenMealHistoryEntity, List<FoodInfoEntity>>

    @Query("SELECT SUM(food.calorie) FROM taken_meal_history as mealHistory " +
            "INNER JOIN food_info as food ON food.takenDate = mealHistory.takenDate" +
            "  AND food.takenTime = mealHistory.takenTime" +
            "  AND food.takenMealType = mealHistory.takenMealType " +
            "WHERE mealHistory.takenDate = :time" +
            "  AND mealHistory.takenMealType = :mealType")
    abstract fun getDayMealTypeTotalCalorie(
        time: Long,
        mealType: String
    ): Map<TakenMealHistoryEntity, List<FoodInfoEntity>>

}