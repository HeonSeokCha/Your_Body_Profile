package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.DrinkInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DrinkDao : BaseDao<DrinkInfoEntity> {

    @Query("""
        SELECT * 
          FROM drink_info
         WHERE insertDate = :time
           AND drinkType = :drinkType
         LIMIT 1
    """)
    abstract fun getDayLastDrinkInfo(drinkType: String, time: Long): Flow<DrinkInfoEntity?>
}