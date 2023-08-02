package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.BodyMeasureInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class BodyMeasureInfoDao : BaseDao<BodyMeasureInfoEntity> {

    @Query("SELECT * FROM body_profile_list ORDER BY lastModifyTme desc")
    abstract fun getSortedBodyProfileList(): Flow<List<BodyMeasureInfoEntity>>

}