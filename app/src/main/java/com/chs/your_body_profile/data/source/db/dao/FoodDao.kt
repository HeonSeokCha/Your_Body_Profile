package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.chs.your_body_profile.data.model.entity.FoodInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FoodDao : BaseDao<FoodInfoEntity> {
}