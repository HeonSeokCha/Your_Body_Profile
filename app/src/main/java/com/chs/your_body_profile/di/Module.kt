package com.chs.your_body_profile.di

import android.content.Context
import androidx.room.Room
import com.chs.your_body_profile.data.source.db.BodyProfileDataBase
import com.chs.your_body_profile.data.source.db.dao.BloodPressureDao
import com.chs.your_body_profile.data.source.db.dao.BloodSugarDao
import com.chs.your_body_profile.data.source.db.dao.DrinkDao
import com.chs.your_body_profile.data.source.db.dao.HemoglobinA1cDao
import com.chs.your_body_profile.data.source.db.dao.InsulinDao
import com.chs.your_body_profile.data.source.db.dao.MedicineDao
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
object Module {

    @Singleton
    @Provides
    fun provideKtorClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }

    @Singleton
    @Provides
    fun provideBodyProfileDatabase(@ApplicationContext app: Context): BodyProfileDataBase {
        return Room.databaseBuilder(
            app,
            BodyProfileDataBase::class.java,
            "body_profile_db"
        )
            .build()
    }

    @Provides
    fun provideBloodPressureDao(bodyProfileDataBase: BodyProfileDataBase): BloodPressureDao {
        return bodyProfileDataBase.bloodPressureDao
    }

    @Provides
    fun provideBloodSugarDao(bodyProfileDataBase: BodyProfileDataBase): BloodSugarDao {
        return bodyProfileDataBase.bloodSugarDao
    }

    @Provides
    fun provideHemoglobinA1cDao(bodyProfileDataBase: BodyProfileDataBase): HemoglobinA1cDao {
        return bodyProfileDataBase.hemoglobinA1cDao
    }

    @Provides
    fun provideDrinkDao(bodyProfileDataBase: BodyProfileDataBase): DrinkDao {
        return bodyProfileDataBase.drinkDao
    }

    @Provides
    fun provideInsulinDao(bodyProfileDataBase: BodyProfileDataBase): InsulinDao {
        return bodyProfileDataBase.insulinDao
    }

    @Provides
    fun provideMedicineDao(bodyProfileDataBase: BodyProfileDataBase): MedicineDao {
        return bodyProfileDataBase.medicineDao
    }

    @Provides
    fun provideWeightDao(bodyProfileDataBase: BodyProfileDataBase): WeightInfoDao {
        return bodyProfileDataBase.weightInfoDao
    }
}
