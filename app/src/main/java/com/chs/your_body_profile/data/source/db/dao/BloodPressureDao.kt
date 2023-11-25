package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.BloodPressureInfoEntity
import kotlinx.coroutines.flow.Flow


@Dao
abstract class BloodPressureDao : BaseDao<BloodPressureInfoEntity> {
    @Query("""
        SELECT * 
          FROM blood_pressure_info
         WHERE measureDate = :time
         ORDER BY measureTime DESC
         LIMIT 1
    """)
    abstract fun getDayLastInfo(time: Long): Flow<BloodPressureInfoEntity?>

    @Query("""
        SELECT * 
          FROM blood_pressure_info
         WHERE measureDate = :time
         ORDER BY measureTime DESC
    """)
    abstract fun getDayInfoList(time: Long): Flow<List<BloodPressureInfoEntity>>

    @Query(
        "SELECT bloodPressure.measureDate, " +
               "bloodPressure1.* " +
          "FROM blood_pressure_info AS bloodPressure " +
         "INNER JOIN blood_pressure_info AS bloodPressure1 ON bloodPressure.measureDate = bloodPressure1.measureDate " +
           "AND bloodPressure.measureTime = bloodPressure1.measureTime " +
         "WHERE bloodPressure.measureDate BETWEEN :startDate AND :endDate " +
         "GROUP BY bloodPressure.measureDate"
    )
    abstract suspend fun getPagingDayInfo(
        startDate: Long,
        endDate: Long
    ): Map<@MapColumn("measureDate") Long, @MapColumn("number") Int>
}