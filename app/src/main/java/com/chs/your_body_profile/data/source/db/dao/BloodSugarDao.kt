package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.BloodSugarInfoEntity
import com.chs.your_body_profile.data.source.db.entity.MealInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class BloodSugarDao : BaseDao<BloodSugarInfoEntity> {
    @Query("""
        SELECT * 
          FROM blood_sugar_info AS bloodSugar
         ORDER BY measureDateTime DESC 
         LIMIT 1
    """)
    abstract fun getDayLastInfo(): Flow<BloodSugarInfoEntity?>

    @Query("""
        SELECT unixepoch(DATE(measureDateTime / 1000, 'unixepoch', 'localtime')) * 1000 as date, *
          FROM blood_sugar_info AS bloodSugar
          LEFT JOIN meal_info AS meal ON bloodSugar.measureDateTime = meal.bloodSugarMeasureTime
         ORDER BY date DESC
         LIMIT 15
         OFFSET :page
    """)
    abstract suspend fun getPagingDayInfoList(page: Int): Map<@MapColumn("date") Long, Map<BloodSugarInfoEntity, List<MealInfoEntity>>>
}