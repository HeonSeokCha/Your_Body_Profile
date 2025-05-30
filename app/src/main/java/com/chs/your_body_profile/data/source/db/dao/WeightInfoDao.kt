package com.chs.your_body_profile.data.source.db.dao

import androidx.paging.PagingSource
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
         ORDER BY measureDateTime DESC LIMIT 1
    """)
    abstract fun getDayLastInfo(): Flow<WeightInfoEntity?>

    @Query("""
        SELECT unixepoch(DATE(measureDateTime / 1000, 'unixepoch', 'localtime')) * 1000 as date, *
          FROM weight_info
         ORDER BY date DESC
         LIMIT 15
         OFFSET :page
    """)
    abstract suspend fun getPagingDayInfoList(
        page: Int
    ): Map<@MapColumn("date") Long, List<WeightInfoEntity>>
}