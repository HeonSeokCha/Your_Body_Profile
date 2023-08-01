package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.BodyProfileListEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class BodyProfileListDao : BaseDao<BodyProfileListEntity> {

    @Query("SELECT * FROM body_profile_list ORDER BY lastModifyTme desc")
    abstract fun getSortedBodyProfileList(): Flow<List<BodyProfileListEntity>>

}