package com.chs.your_body_profile.data.source.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.chs.your_body_profile.R
import com.chs.your_body_profile.data.source.db.dao.*
import com.chs.your_body_profile.data.model.entity.*
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

@Database(
    entities = [
        BodyMeasureInfoEntity::class,
        BloodPressureInfoEntity::class,
        BloodSugarInfoEntity::class,
        DrinkInfoEntity::class,
        FoodInfoEntity::class,
        InsulinInfoEntity::class,
        HemoglobinA1cInfoEntity::class,
        MedicineInfoEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BodyProfileDataBase : RoomDatabase() {
    abstract val bodyMeasureInfoDao: BodyMeasureInfoDao
    abstract val bloodPressureDao: BloodPressureDao
    abstract val bloodSugarDao: BloodSugarDao
    abstract val drinkDao: DrinkDao
    abstract val insulinDao: InsulinDao
    abstract val hemoglobinA1cDao: HemoglobinA1cDao
    abstract val medicineDao: MedicineDao

    companion object {
        fun getInstance(context: Context): BodyProfileDataBase {
            return Room
                .databaseBuilder(
                    context,
                    BodyProfileDataBase::class.java,
                    "body_profile_db"
                )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Executors.newSingleThreadExecutor().execute {
                            runBlocking {
                                getInstance(context).bodyMeasureInfoDao.insert(
                                    *defaultBodyProfileList(context).mapIndexed { index, pair ->
                                        BodyMeasureInfoEntity(
                                            title = pair.first,
                                            unit = pair.second,
                                            lastModifyTme = index.toLong()
                                        )
                                    }.toTypedArray()
                                )
                            }
                        }
                    }
                })
                .build()
        }

        private fun defaultBodyProfileList(context: Context): List<Pair<String, String>> {
            return listOf(
                context.getString(R.string.text_water) to context.getString(R.string.text_drink_unit),
                context.getString(R.string.text_coffee) to context.getString(R.string.text_drink_unit),
                context.getString(R.string.text_food) to context.getString(R.string.text_food_unit),
                context.getString(R.string.text_blood_sugar) to context.getString(R.string.text_blood_sugar_unit),
                context.getString(R.string.text_blood_pressure) to context.getString(R.string.text_blood_pressure_unit),
                context.getString(R.string.text_hemoglobin_A1c) to context.getString(R.string.text_hemoglobin_A1c_unit),
                context.getString(R.string.text_insulin) to context.getString(R.string.text_insulin_unit),
                context.getString(R.string.text_take_medicine) to context.getString(R.string.text_take_medicine_unit),
            )
        }
    }
}