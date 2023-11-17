package com.chs.your_body_profile.data.module

import android.content.Context
import com.chs.your_body_profile.data.source.api.FoodService
import com.chs.your_body_profile.data.source.db.BodyProfileDataBase
import com.chs.your_body_profile.data.source.db.dao.BloodPressureDao
import com.chs.your_body_profile.data.source.db.dao.BloodSugarDao
import com.chs.your_body_profile.data.source.db.dao.DrinkDao
import com.chs.your_body_profile.data.source.db.dao.FoodDao
import com.chs.your_body_profile.data.source.db.dao.FoodSearchHistoryDao
import com.chs.your_body_profile.data.source.db.dao.HemoglobinA1cDao
import com.chs.your_body_profile.data.source.db.dao.InsulinDao
import com.chs.your_body_profile.data.source.db.dao.MedicineDao
import com.chs.your_body_profile.data.source.db.dao.TakenMealHistoryDao
import com.chs.your_body_profile.data.source.db.dao.WeightInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourceModule {

    @Singleton
    @Provides
    fun provideKtorClient(): FoodService {
        return FoodService(
            HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {
                    json(Json { ignoreUnknownKeys = true })
                }
            }
        )
    }

    @Singleton
    @Provides
    fun provideBodyProfileDatabase(@ApplicationContext app: Context): BodyProfileDataBase {
        return BodyProfileDataBase.getInstance(app)
    }

    @Singleton
    @Provides
    fun provideBloodPressureDao(bodyProfileDataBase: BodyProfileDataBase): BloodPressureDao {
        return bodyProfileDataBase.bloodPressureDao
    }

    @Singleton
    @Provides
    fun provideBloodSugarDao(bodyProfileDataBase: BodyProfileDataBase): BloodSugarDao {
        return bodyProfileDataBase.bloodSugarDao
    }

    @Singleton
    @Provides
    fun provideHemoglobinA1cDao(bodyProfileDataBase: BodyProfileDataBase): HemoglobinA1cDao {
        return bodyProfileDataBase.hemoglobinA1cDao
    }

    @Singleton
    @Provides
    fun provideDrinkDao(bodyProfileDataBase: BodyProfileDataBase): DrinkDao {
        return bodyProfileDataBase.drinkDao
    }

    @Singleton
    @Provides
    fun provideInsulinDao(bodyProfileDataBase: BodyProfileDataBase): InsulinDao {
        return bodyProfileDataBase.insulinDao
    }

    @Singleton
    @Provides
    fun provideMedicineDao(bodyProfileDataBase: BodyProfileDataBase): MedicineDao {
        return bodyProfileDataBase.medicineDao
    }

    @Singleton
    @Provides
    fun provideWeightDao(bodyProfileDataBase: BodyProfileDataBase): WeightInfoDao {
        return bodyProfileDataBase.weightInfoDao
    }

    @Singleton
    @Provides
    fun provideFoodDao(bodyProfileDataBase: BodyProfileDataBase): FoodDao {
        return bodyProfileDataBase.foodDao
    }

    @Singleton
    @Provides
    fun provideFoodSearchHistoryDao(bodyProfileDataBase: BodyProfileDataBase): FoodSearchHistoryDao {
        return bodyProfileDataBase.foodSearchHistoryDao
    }

    @Singleton
    @Provides
    fun provideTakenMealHistoryDao(bodyProfileDataBase: BodyProfileDataBase): TakenMealHistoryDao {
        return bodyProfileDataBase.takenMealHistoryDao
    }

    @Singleton
    @Provides
    fun provideTakenMealInfoDao(bodyProfileDataBase: BodyProfileDataBase): TakenMealDao {
        return bodyProfileDataBase.takenMealInfoDao
    }
}