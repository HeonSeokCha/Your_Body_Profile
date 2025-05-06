package com.chs.your_body_profile.data.source.db.dao
import androidx.room.Dao
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
        SELECT * 
          FROM insulin_info
         WHERE DATE(injectDateTime / 1000, 'unixepoch', 'localtime') = DATE(:time / 1000, 'unixepoch', 'localtime')
         ORDER BY injectDateTime DESC 
    """)
    abstract suspend fun getDayInfoList(time: Long): List<InsulinInfoEntity>
}