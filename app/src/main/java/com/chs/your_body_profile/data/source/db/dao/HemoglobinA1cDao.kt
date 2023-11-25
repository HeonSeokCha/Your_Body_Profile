package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.HemoglobinA1cInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class HemoglobinA1cDao : BaseDao<HemoglobinA1cInfoEntity> {
    @Query(
        "SELECT * " +
          "FROM hemoglobin_a1c_info " +
         "WHERE measureDate = :time "
    )
    abstract fun getDayInfo(time: Long): Flow<HemoglobinA1cInfoEntity?>

    @Query(
        "SELECT measureDate," +
               "CAST(number AS INT) AS number " +
          "FROM hemoglobin_a1c_info " +
         "WHERE measureDate BETWEEN :beginTime AND :endTime " +
         "GROUP BY measureDate"
    )
    abstract suspend fun getDayPagingInfo(
        beginTime: Long,
        endTime: Long
    ): Map<@MapColumn("measureDate") Long, @MapColumn("number") Int>
}