package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.BloodPressureInfoEntity


@Dao
abstract class BloodPressureDao : BaseDao<BloodPressureInfoEntity> {
    @Query("""SELECT * 
                FROM blood_pressure_info
               WHERE insertTime > :time
               ORDER BY insertTime DESC LIMIT 1""")
    abstract suspend fun getDayBloodPressureInfo(time: Long): BloodPressureInfoEntity?
}