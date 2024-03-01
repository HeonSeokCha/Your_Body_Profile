package com.chs.your_body_profile.data.source.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chs.your_body_profile.data.source.db.dao.*
import com.chs.your_body_profile.data.source.db.entity.*

@Database(
    entities = [
        BloodPressureInfoEntity::class,
        BloodSugarInfoEntity::class,
        DrinkInfoEntity::class,
        FoodInfoEntity::class,
        MealHistoryEntity::class,
        MealHistoryWithFood::class,
        InsulinInfoEntity::class,
        HemoglobinA1cInfoEntity::class,
        MedicineInfoEntity::class,
        WeightInfoEntity::class,
        FoodSearchHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BodyProfileDataBase : RoomDatabase() {
    abstract val bloodPressureDao: BloodPressureDao
    abstract val bloodSugarDao: BloodSugarDao
    abstract val drinkDao: DrinkDao
    abstract val insulinDao: InsulinDao
    abstract val hemoglobinA1cDao: HemoglobinA1cDao
    abstract val medicineDao: MedicineDao
    abstract val weightInfoDao: WeightInfoDao
    abstract val foodDao: FoodDao
    abstract val mealHistoryDao: MealHistoryDao
    abstract val foodSearchHistoryDao: FoodSearchHistoryDao
    abstract val mealHistoryWithFoodDao: MealHistoryWithFoodDao

}