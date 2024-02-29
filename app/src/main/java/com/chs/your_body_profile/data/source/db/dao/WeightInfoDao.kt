package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.WeightInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WeightInfoDao : BaseDao<WeightInfoEntity> {

    @Query("""
        SELECT * 
          FROM weight_info
         WHERE measureDate = :time
         ORDER BY measureTime DESC LIMIT 1
    """)
    abstract fun getDayLastInfo(time: Long): Flow<WeightInfoEntity?>

    @Query("""
        SELECT * 
          FROM weight_info
         WHERE measureDate = :time
         ORDER BY measureTime DESC
    """)
    abstract fun getDayInfoList(time: Long): Flow<List<WeightInfoEntity>>


    @Query(
        "SELECT measureDate, " +
               "CAST(weight AS INT) AS weight " +
          "FROM weight_info " +
         "WHERE measureDate BETWEEN :beginTime AND :endTime " +
         "GROUP BY measureDate "
    )
    abstract suspend fun getDayPagingInfo(
        beginTime: Long,
        endTime: Long
    ): Map<@MapColumn("measureDate") Long, @MapColumn("weight") Int>
}