package com.chs.your_body_profile.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chs.your_body_profile.data.source.db.dao.*
import com.chs.your_body_profile.data.source.db.entity.*

@Database(
    entities = [
        BloodPressureInfoEntity::class,
        BloodSugarInfoEntity::class,
        DrinkInfoEntity::class,
        InsulinInfoEntity::class,
        HemoglobinA1cInfoEntity::class,
        WeightInfoEntity::class,
        PayInfoEntity::class ,
        MealInfoEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class BodyProfileDataBase : RoomDatabase() {
    abstract val bloodPressureDao: BloodPressureDao
    abstract val bloodSugarDao: BloodSugarDao
    abstract val drinkDao: DrinkDao
    abstract val insulinDao: InsulinDao
    abstract val hemoglobinA1cDao: HemoglobinA1cDao
    abstract val weightInfoDao: WeightInfoDao
    abstract val payInfoDao: PayInfoDao
    abstract val mealInfoDao: MealInfoDao
}