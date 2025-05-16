package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.HemoglobinA1cInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class HemoglobinA1cDao : BaseDao<HemoglobinA1cInfoEntity> {
    @Query(
        "SELECT * " +
          "FROM hemoglobin_a1c_info " +
         "WHERE DATE(measureDateTime / 1000, 'unixepoch', 'localtime') = DATE(:time / 1000, 'unixepoch', 'localtime') " +
         "ORDER BY measureDateTime DESC " +
         "LIMIT 1"
    )
    abstract fun getDayLastInfo(time: Long): Flow<HemoglobinA1cInfoEntity?>

    @Query("""
        SELECT DATE(measureDateTime / 1000, 'unixepoch', 'localtime') as date, * 
          FROM hemoglobin_a1c_info 
         GROUP BY date
         ORDER BY date DESC
         LIMIT 15
         OFFSET :page
    """)
    abstract fun getPagingDayInfoList(page: Int): Map<@MapColumn("date") Long, List<HemoglobinA1cInfoEntity>>
}