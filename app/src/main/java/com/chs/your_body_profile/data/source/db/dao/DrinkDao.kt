package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import com.chs.your_body_profile.data.model.entity.DrinkInfoEntity

@Dao
abstract class DrinkDao : BaseDao<DrinkInfoEntity> {
}