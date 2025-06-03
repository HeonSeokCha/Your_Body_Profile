package com.chs.your_body_profile.di

import com.chs.your_body_profile.data.repository.BloodPressureRepositoryImpl
import com.chs.your_body_profile.data.repository.BloodSugarRepositoryImpl
import com.chs.your_body_profile.data.repository.DrinkRepositoryImpl
import com.chs.your_body_profile.data.repository.HemoglobinA1cRepositoryImpl
import com.chs.your_body_profile.data.repository.InsulinInfoRepositoryImpl
import com.chs.your_body_profile.data.repository.MedicineRepositoryImpl
import com.chs.your_body_profile.data.repository.PayInfoRepositoryImpl
import com.chs.your_body_profile.data.repository.WeightRepositoryImpl
import com.chs.your_body_profile.domain.repository.BloodPressureRepository
import com.chs.your_body_profile.domain.repository.BloodSugarRepository
import com.chs.your_body_profile.domain.repository.DrinkRepository
import com.chs.your_body_profile.domain.repository.FoodRepository
import com.chs.your_body_profile.domain.repository.HemoglobinA1cRepository
import com.chs.your_body_profile.domain.repository.InsulinRepository
import com.chs.your_body_profile.domain.repository.MedicineRepository
import com.chs.your_body_profile.domain.repository.PayInfoRepository
import com.chs.your_body_profile.domain.repository.WeightRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindBloodPressureRepository(bloodPressureRepositoryImpl: BloodPressureRepositoryImpl): BloodPressureRepository

    @Binds
    abstract fun bindBloodSugarRepository(bloodSugarRepositoryImpl: BloodSugarRepositoryImpl): BloodSugarRepository

    @Binds
    abstract fun bindDrinkRepository(drinkRepositoryImpl: DrinkRepositoryImpl): DrinkRepository

    @Binds
    abstract fun binHemoglobinA1cRepository(hemoglobinA1cRepositoryImpl: HemoglobinA1cRepositoryImpl): HemoglobinA1cRepository

    @Binds
    abstract fun bindInsulinRepository(insulinInfoRepositoryImpl: InsulinInfoRepositoryImpl): InsulinRepository

    @Binds
    abstract fun bindMedicineRepository(medicineRepositoryImpl: MedicineRepositoryImpl): MedicineRepository

    @Binds
    abstract fun bindWeightRepository(weightRepositoryImpl: WeightRepositoryImpl): WeightRepository

    @Binds
    abstract fun bindPayInfoRepository(payInfoRepositoryImpl: PayInfoRepositoryImpl): PayInfoRepository
}
