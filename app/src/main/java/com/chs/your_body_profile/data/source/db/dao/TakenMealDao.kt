package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.TakenMealInfoEntity

@Dao
abstract class TakenMealDao : BaseDao<TakenMealInfoEntity> {
    @Query(
        "DELETE FROM taken_meal_info " +
         "WHERE takenDate = :takenDate " +
           "AND takenMealType = :mealTYpe"
    )
    abstract suspend fun deleteTakenMealInfo(
        takenDate: Long,
        mealTYpe: Int
    )
}