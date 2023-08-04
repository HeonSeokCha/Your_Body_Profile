package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.BodySummaryInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class BodySummaryDao : BaseDao<BodySummaryInfoEntity> {

    @Query("""
        SELECT * 
          FROM body_summary_info 
         WHERE insertDate = :insertDate
         ORDER BY lastModifyTime DESC
         """)
    abstract fun getTodayBodySummaryInfo(insertDate: Long): Flow<List<BodySummaryInfoEntity>>
}
