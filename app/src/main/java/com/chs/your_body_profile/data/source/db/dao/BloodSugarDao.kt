package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.BloodSugarInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class BloodSugarDao : BaseDao<BloodSugarInfoEntity> {
    @Query("""
        SELECT * 
          FROM blood_sugar_info
         WHERE DATE(measureDateTime / 1000, 'unixepoc', 'localtime') = DATE(:time / 1000, 'unixepoc', 'localtime')
         ORDER BY measureDateTime DESC 
         LIMIT 1
    """)
    abstract suspend fun getDayLastInfo(time: Long): BloodSugarInfoEntity?

    @Query("""
        SELECT * 
          FROM blood_sugar_info
         WHERE DATE(measureDateTime / 1000, 'unixepoc', 'localtime') = DATE(:time / 1000, 'unixepoc', 'localtime')
         ORDER BY measureDateTime DESC 
    """)
    abstract suspend fun getDayInfoList(time: Long): List<BloodSugarInfoEntity>

    @Query("SELECT IFNULL(MIN(number), 0) FROM blood_sugar_info WHERE measureDateTime = :time")
    abstract fun getDayMinInfo(time: Long): Flow<Int>

    @Query("SELECT IFNULL(MAX(number), 0) FROM blood_sugar_info WHERE measureDateTime = :time")
    abstract fun getDayMaxInfo(time: Long): Flow<Int>

    @Query("SELECT IFNULL(AVG(number), 0) FROM BLOOD_SUGAR_INFO WHERE measureDateTime = :time")
    abstract fun getDayAvgInfo(time: Long): Flow<Int>
}