package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.InsulinInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class InsulinDao : BaseDao<InsulinInfoEntity> {

    @Query("""
        SELECT * 
          FROM insulin_info 
         WHERE insertDate = :time
         ORDER BY lastModifyTime DESC
         LIMIT 1
    """)
    abstract fun getDayLastInfo(time: Long): Flow<InsulinInfoEntity?>
}