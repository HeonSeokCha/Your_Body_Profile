package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.DrinkInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DrinkDao : BaseDao<DrinkInfoEntity> {

    @Query(
        "SELECT * " +
          "FROM drink_info " +
         "WHERE DATE(takenDate / 1000, 'unixepoch', 'localtime') = DATE(:targetDate / 1000, 'unixepoch', 'localtime') " +
           "AND drinkType = :drinkType LIMIT 1"
    )
    abstract fun getDayLastDrinkInfo(
        drinkType: String,
        targetDate: Long
    ): Flow<DrinkInfoEntity?>


    @Query(
        "SELECT * " +
          "FROM drink_info " +
         "WHERE takenDate BETWEEN :beginDate AND :endDate " +
           "AND drinkType = :drinkType " +
         "ORDER BY takenDate DESC "
    )
    abstract suspend fun getDayDrinkInfoList(
        beginDate: Long,
        endDate: Long,
        drinkType: String,
    ): List<DrinkInfoEntity>
}