package com.chs.your_body_profile.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chs.your_body_profile.data.source.db.entity.BloodPressureInfoEntity
import com.chs.your_body_profile.data.source.db.entity.BloodSugarInfoEntity
import com.chs.your_body_profile.data.source.db.entity.DrinkInfoEntity
import com.chs.your_body_profile.data.source.db.entity.FoodInfoEntity
import com.chs.your_body_profile.data.source.db.entity.InsulinInfoEntity
import com.chs.your_body_profile.data.source.db.entity.MedicineInfoEntity

@Database(
    entities = [
        BloodPressureInfoEntity::class,
        BloodSugarInfoEntity::class,
        DrinkInfoEntity::class,
        FoodInfoEntity::class,
        InsulinInfoEntity::class,
        MedicineInfoEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BodyProfileDataBase : RoomDatabase() {
}