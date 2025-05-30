package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.DrinkInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DrinkDao : BaseDao<DrinkInfoEntity> {

    @Query(
        "SELECT * " +
          "FROM drink_info " +
         "WHERE DATE(takenDateTime / 1000, 'unixepoch', 'localtime') = DATE(:targetDate / 1000, 'unixepoch', 'localtime') " +
           "AND drinkType = :drinkType " +
         "ORDER BY takenDateTime DESC "
    )
    abstract fun getDayInfoList(
        targetDate: Long,
        drinkType: String
    ): Flow<List<DrinkInfoEntity>>


    @Query("""
        SELECT unixepoch(DATE(takenDateTime / 1000, 'unixepoch', 'localtime')) * 1000 as date, *
          FROM drink_info
         WHERE drinkType = :drinkType
         ORDER BY date DESC
         LIMIT 15
         OFFSET :page
    """)
    abstract suspend fun getPagingDayInfoList(
        drinkType: String,
        page: Int
    ): Map<@MapColumn("date") Long, List<DrinkInfoEntity>>
}