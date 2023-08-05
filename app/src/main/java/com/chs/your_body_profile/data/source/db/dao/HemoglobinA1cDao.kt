package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.HemoglobinA1cInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class HemoglobinA1cDao : BaseDao<HemoglobinA1cInfoEntity> {
    @Query("""
        SELECT * 
          FROM hemoglobin_a1c_info 
         WHERE insertDate = :time
         LIMIT 1
    """)
    abstract override fun getDayLastInfo(time: Long): Flow<HemoglobinA1cInfoEntity?>
}