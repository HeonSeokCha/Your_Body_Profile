package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.source.db.entity.MedicineInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MedicineDao : BaseDao<MedicineInfoEntity> {

    @Query("""
        SELECT * 
          FROM medicine_info
         WHERE DATE(takenDateTime / 1000, 'unixepoch', 'localtime') =  DATE(:time / 1000, 'unixepoch', 'localtime')
         ORDER BY takenDateTime DESC LIMIT 1
    """)
    abstract fun getDayLastInfo(time: Long): Flow<MedicineInfoEntity?>

    @Query("""
        SELECT * 
          FROM medicine_info
         WHERE DATE(takenDateTime / 1000, 'unixepoch', 'localtime') =  DATE(:time / 1000, 'unixepoch', 'localtime')
         ORDER BY takeMedicineType DESC
    """)
    abstract suspend fun getDayInfoList(time: Long): List<MedicineInfoEntity>
}