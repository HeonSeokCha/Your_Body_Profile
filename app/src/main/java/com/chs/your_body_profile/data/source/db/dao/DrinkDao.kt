package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.DrinkInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DrinkDao : BaseDao<DrinkInfoEntity> {

    @Query(
        "SELECT * " +
          "FROM drink_info " +
         "WHERE takenDate = :time " +
           "AND drinkType = :drinkType "
    )
    abstract fun getDayLastDrinkInfo(
        drinkType: String,
        time: Long
    ): Flow<DrinkInfoEntity?>


    @Query(
        ""
    )
    abstract suspend fun getDayPagingInfo(
        beginTime: Long,
        endTime: Long,
        drinkType: String
    ): Map<@MapColumn("takenDate") Long, @MapColumn("totalCups") Int>

}