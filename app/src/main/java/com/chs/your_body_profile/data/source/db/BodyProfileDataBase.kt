package com.chs.your_body_profile.data.source.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.chs.your_body_profile.R
import com.chs.your_body_profile.data.source.db.dao.*
import com.chs.your_body_profile.data.model.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

@Database(
    entities = [
        BodySummaryInfoEntity::class,
        BloodPressureInfoEntity::class,
        BloodSugarInfoEntity::class,
        DrinkInfoEntity::class,
        FoodInfoEntity::class,
        InsulinInfoEntity::class,
        HemoglobinA1cInfoEntity::class,
        MedicineInfoEntity::class,
        WeightInfoEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BodyProfileDataBase : RoomDatabase() {
    abstract val bodySummaryDao: BodySummaryDao
    abstract val bloodPressureDao: BloodPressureDao
    abstract val bloodSugarDao: BloodSugarDao
    abstract val drinkDao: DrinkDao
    abstract val insulinDao: InsulinDao
    abstract val hemoglobinA1cDao: HemoglobinA1cDao
    abstract val medicineDao: MedicineDao
    abstract val weightInfoDao: WeightInfoDao

    companion object {
        fun getInstance(context: Context): BodyProfileDataBase {
            return Room
                .databaseBuilder(
                    context,
                    BodyProfileDataBase::class.java,
                    "body_profile_db"
                )
                .build()
        }
    }
}