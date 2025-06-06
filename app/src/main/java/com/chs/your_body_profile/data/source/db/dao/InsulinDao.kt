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
         "ORDER BY injectDateTime DESC " +
         "LIMIT 1"
    )
    abstract fun getDayLastInfo(): Flow<InsulinInfoEntity?>

    @Query("""
        SELECT unixepoch(DATE(injectDateTime / 1000, 'unixepoch', 'localtime')) * 1000 as date, *
          FROM insulin_info
         ORDER BY date DESC
         LIMIT 15
         OFFSET :page
    """)
    abstract suspend fun getPagingDayInfoList(page: Int): Map<@MapColumn("date") Long, List<InsulinInfoEntity>>
}