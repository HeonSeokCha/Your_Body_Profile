package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.MedicineInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MedicineDao : BaseDao<MedicineInfoEntity> {

    @Query("""
        SELECT * 
          FROM medicine_info
         WHERE takenDate = :time
         ORDER BY takenTime DESC LIMIT 1
    """)
    abstract fun getDayLastInfo(time: Long): Flow<MedicineInfoEntity?>

    @Query("""
        SELECT * 
          FROM medicine_info
         WHERE takenDate = :time
         ORDER BY takeMedicineType DESC
    """)
    abstract fun getDayInfoList(time: Long): Flow<List<MedicineInfoEntity>>
}