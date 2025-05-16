package com.chs.your_body_profile.data.source.db.dao
import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.InsulinInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class InsulinDao : BaseDao<InsulinInfoEntity> {

    @Query(
        "SELECT * " +
          "FROM insulin_info " +
         "WHERE DATE(injectDateTime / 1000, 'unixepoch', 'localtime') = DATE(:time / 1000, 'unixepoch', 'localtime') " +
         "LIMIT 1"
    )
    abstract fun getDayLastInfo(time: Long): Flow<InsulinInfoEntity?>

    @Query("""
        SELECT DATE(injectDateTime / 1000, 'unixepoch', 'localtime') as date, * 
          FROM insulin_info
         GROUP BY date
         ORDER BY date DESC
         LIMIT 15
         OFFSET :page
    """)
    abstract fun getPagingDayInfoList(page: Int): Map<@MapColumn("date") Long, List<InsulinInfoEntity>>
}