package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.BloodSugarInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class BloodSugarDao : BaseDao<BloodSugarInfoEntity> {
    @Query("""
        SELECT * 
          FROM blood_sugar_info
         WHERE insertDate = :time
         ORDER BY measureTime DESC 
         LIMIT 1
    """)
    abstract fun getDayLastInfo(time: Long): Flow<BloodSugarInfoEntity?>

    @Query("""
        SELECT * 
          FROM blood_sugar_info
         WHERE insertDate = :time
         ORDER BY measureTime DESC
    """)
    abstract fun getDayInfoList(time: Long): Flow<List<BloodSugarInfoEntity>>
}