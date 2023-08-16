package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.BloodPressureInfoEntity
import kotlinx.coroutines.flow.Flow


@Dao
abstract class BloodPressureDao : BaseDao<BloodPressureInfoEntity> {
    @Query("""SELECT * 
                FROM blood_pressure_info
               WHERE insertDate = :time
               ORDER BY measureTime DESC
               LIMIT 1
    """)
    abstract fun getDayLastInfo(time: Long): Flow<BloodPressureInfoEntity?>

    @Query("""SELECT * 
                FROM blood_pressure_info
               WHERE insertDate = :time
               ORDER BY measureTime DESC
    """)
    abstract fun getDayInfoList(time: Long): Flow<List<BloodPressureInfoEntity>>
}