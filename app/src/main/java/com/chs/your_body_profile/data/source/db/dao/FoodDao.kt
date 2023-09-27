package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.FoodInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FoodDao : BaseDao<FoodInfoEntity> {
    @Query("SELECT IFNULL(SUM(calorie), 0) FROM food_info WHERE takeMealTime " +
            "BETWEEN :startTime AND :endTime")
    abstract fun getDayTotalCalories(
        startTime: Long,
        endTime: Long
    ): Flow<Int>

    @Query("SELECT * FROM food_info WHERE (takeMealTime " +
            "BETWEEN :startTime AND :endTime) " +
            "AND mealType = :mealType")
    abstract fun getDayMealTypeFoodList(
        startTime: Long,
        endTime: Long,
        mealType: String
    ): Flow<List<FoodInfoEntity>>

    @Query("SELECT IFNULL(SUM(calorie), 0) FROM food_info WHERE (takeMealTime " +
            "BETWEEN :startTime AND :endTime) " +
            "AND mealType = :mealType")
    abstract fun getDayMealTypeTotalCalories(
        startTime: Long,
        endTime: Long,
        mealType: String
    ): Flow<Int>

    @Query("SELECT IFNULL(SUM(carbohydrate), 0) FROM food_info WHERE (takeMealTime " +
            "BETWEEN :startTime AND :endTime) " +
            "AND mealType = :mealType")
    abstract fun getDayMealTypeTotalCarbohydrate(
        startTime: Long,
        endTime: Long,
        mealType: String
    ): Flow<Float>

    @Query("SELECT IFNULL(SUM(fat), 0) FROM food_info WHERE (takeMealTime " +
            "BETWEEN :startTime AND :endTime) " +
            "AND mealType = :mealType")
    abstract fun getDayMealTypeTotalFat(
        startTime: Long,
        endTime: Long,
        mealType: String
    ): Flow<Float>

    @Query("SELECT IFNULL(SUM(protein), 0) FROM food_info WHERE (takeMealTime " +
            "BETWEEN :startTime AND :endTime) " +
            "AND mealType = :mealType")
    abstract fun getDayMealTypeTotalProtein(
        startTime: Long,
        endTime: Long,
        mealType: String
    ): Flow<Float>
}