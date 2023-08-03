package com.chs.your_body_profile.data.repository

import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toBloodPressureInfo
import com.chs.your_body_profile.data.source.db.dao.*
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.domain.repository.BodyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class BodyRepositoryImpl @Inject constructor(
     private val bloodPressureDao: BloodPressureDao,
     private val bloodSugarDao: BloodSugarDao,
     private val drinkDao: DrinkDao,
     private val insulinDao: InsulinDao,
     private val hemoglobinA1cDao: HemoglobinA1cDao,
     private val medicineDao: MedicineDao,
     private val weightInfoDao: WeightInfoDao
) : BodyRepository {

    override fun getDayLastBloodPressureInfo(localDateTime: LocalDateTime): Flow<BloodPressureInfo?> {
        return bloodPressureDao.getDayLastInfo(localDateTime.toMillis()).map {
            it?.toBloodPressureInfo()
        }
    }

    override fun getDayLastBloodSugarInfo(localDateTime: LocalDateTime): Flow<BloodSugarInfo?> {
        TODO("Not yet implemented")
    }

    override fun getDayLastDrinkWaterInfo(localDateTime: LocalDateTime): Flow<DrinkWaterInfo?> {
        TODO("Not yet implemented")
    }

    override suspend fun updateWaterDrinkWaterInfo(drinkWaterInfo: DrinkWaterInfo) {
        TODO("Not yet implemented")
    }

    override fun getDayLastDrinkCoffeeInfo(localDateTime: LocalDateTime): Flow<DrinkCoffeeInfo?> {
        TODO("Not yet implemented")
    }

    override suspend fun updateWaterDrinkCoffeeInfo(drinkCoffeeInfo: DrinkCoffeeInfo) {
        TODO("Not yet implemented")
    }

    override fun getDayLastHemoglobinA1cInfo(localDateTime: LocalDateTime): Flow<HemoglobinA1cInfo?> {
        TODO("Not yet implemented")
    }

    override fun getDayLastInsulinInfo(localDateTime: LocalDateTime): Flow<InsulinInfo?> {
        TODO("Not yet implemented")
    }

    override fun getDayMedicineInfo(localDateTime: LocalDateTime): Flow<MedicineInfo?> {
        TODO("Not yet implemented")
    }

    override fun getDayWeightInfo(localDateTime: LocalDateTime): Flow<WeightInfo?> {
        TODO("Not yet implemented")
    }
}