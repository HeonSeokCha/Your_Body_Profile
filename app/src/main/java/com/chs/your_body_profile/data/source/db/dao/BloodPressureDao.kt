package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.BloodPressureInfoEntity
import kotlinx.coroutines.flow.Flow


@Dao
abstract class BloodPressureDao : BaseDao<BloodPressureInfoEntity> {
    @Query("""
        SELECT * 
          FROM blood_pressure_info
         WHERE DATE(measureDateTime / 1000, 'unixepoch', 'localtime') = DATE(:time / 1000, 'unixepoch', 'localtime')
         ORDER BY measureDateTime DESC
         LIMIT 1
    """)
    abstract fun getDayLastInfo(time: Long): Flow<BloodPressureInfoEntity?>

    @Query("""
        SELECT DATE(measureDateTime / 1000, 'unixepoch', 'localtime') as date, * 
          FROM blood_pressure_info
         GROUP BY date
         ORDER BY date DESC
         LIMIT 15
         OFFSET :page
    """)
    abstract fun getPagingDayInfoList(page: Int): Map<@MapColumn("date") Long, List<BloodPressureInfoEntity>>
}