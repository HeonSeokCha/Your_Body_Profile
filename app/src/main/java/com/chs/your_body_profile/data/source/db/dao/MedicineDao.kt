package com.chs.your_body_profile.data.source.db.dao

import androidx.room.Dao
import com.chs.your_body_profile.data.model.entity.MedicineInfoEntity

@Dao
abstract class MedicineDao : BaseDao<MedicineInfoEntity> {

}