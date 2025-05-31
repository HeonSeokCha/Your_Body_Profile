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
         "ORDER BY measureDateTime DESC " +
         "LIMIT 1"
    )
    abstract fun getDayLastInfo(): Flow<HemoglobinA1cInfoEntity?>

    @Query("""
        SELECT unixepoch(DATE(measureDateTime / 1000, 'unixepoch', 'localtime')) * 1000 as date, *
          FROM hemoglobin_a1c_info 
         ORDER BY date DESC
         LIMIT 15
         OFFSET :page
    """)
    abstract suspend fun getPagingDayInfoList(page: Int): Map<@MapColumn("date") Long, List<HemoglobinA1cInfoEntity>>
}