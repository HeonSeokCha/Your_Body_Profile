package com.chs.your_body_profile.data.source.db.dao
import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.InsulinInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class InsulinDao : BaseDao<InsulinInfoEntity> {

    @Query(
        "SELECT * " +
          "FROM insulin_info " +
         "WHERE injectDate = :time " +
         "LIMIT 1"
    )
    abstract fun getDayInfo(time: Long): Flow<InsulinInfoEntity?>

    @Query(
        "SELECT injectDate, " +
               "level " +
          "FROM insulin_info " +
        "WHERE injectDate BETWEEN :startDate AND :endDate " +
        "GROUP BY injectDate"
    )
    abstract suspend fun getPagingDayInfo(
        startDate: Long,
        endDate: Long
    ): Map<@MapColumn("injectDate") Long, @MapColumn("level") Int>
}