package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.WeightInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WeightInfoDao : BaseDao<WeightInfoEntity> {

    @Query("""
        SELECT * 
          FROM weight_info
         WHERE insertDate = :time
         ORDER BY lastModifyTime DESC LIMIT 1
    """)
    abstract fun getDayLastInfo(time: Long): Flow<WeightInfoEntity?>

    @Query("""
        SELECT * 
          FROM weight_info
         WHERE insertDate = :time
         ORDER BY lastModifyTime DESC
    """)
    abstract fun getDayInfoList(time: Long): Flow<List<WeightInfoEntity>>
}