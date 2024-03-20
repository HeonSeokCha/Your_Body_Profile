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

    @Query(
        "SELECT * " +
          "FROM hemoglobin_a1c_info " +
         "WHERE DATE(measureDateTime / 1000, 'unixepoch', 'localtime') = DATE(:time / 1000, 'unixepoch', 'localtime') " +
        "ORDER By measureDateTime DESC"
    )
    abstract suspend fun getDayInfo(time: Long): List<HemoglobinA1cInfoEntity>
}