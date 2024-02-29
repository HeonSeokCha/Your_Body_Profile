package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import com.chs.your_body_profile.data.source.db.entity.FoodInfoEntity

@Dao
abstract class FoodDao : BaseDao<FoodInfoEntity>