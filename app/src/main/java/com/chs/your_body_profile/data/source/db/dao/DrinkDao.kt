package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.DrinkInfoEntity
import com.chs.your_body_profile.domain.model.DrinkType
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DrinkDao : BaseDao<DrinkInfoEntity> {

    @Query(
        "SELECT * " +
          "FROM drink_info " +
         "WHERE DATE(takenDate / 1000, 'unixepoch', 'localtime') = DATE(:time / 1000, 'unixepoch', 'localtime') " +
           "AND drinkType = :drinkType "
    )
    abstract suspend fun getDayLastDrinkInfo(
        drinkType: String,
        time: Long
    ): DrinkInfoEntity?


    @Query(
        "SELECT DATE(takenDate / 1000, 'unixepoch', 'localtime') as 'date', * " +
          "FROM drink_info " +
         "WHERE DATE(takenDate / 1000, 'unixepoch', 'localtime') = DATE(:time / 1000, 'unixepoch', 'localtime') " +
           "AND drinkType = :drinkType "
    )
    abstract suspend fun getDayDrinkInfoList(
        time: Long,
        drinkType: Int,
    ): List<DrinkInfoEntity>

}