package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.MealInfoEntity

@Dao
abstract class MealInfoDao : BaseDao<MealInfoEntity> {
    @Query("DELETE FROM meal_info WHERE bloodSugarMeasureTime = :bloodMeasureTime")
    abstract suspend fun deleteFromBloodSugar(bloodMeasureTime: Long)
}