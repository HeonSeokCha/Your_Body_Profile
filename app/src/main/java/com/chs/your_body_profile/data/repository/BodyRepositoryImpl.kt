package com.chs.your_body_profile.data.repository

import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toBloodPressureInfo
import com.chs.your_body_profile.data.mapper.toBloodPressureInfoEntity
import com.chs.your_body_profile.data.mapper.toBloodSugarInfo
import com.chs.your_body_profile.data.mapper.toBloodSugarInfoEntity
import com.chs.your_body_profile.data.mapper.toDrinkCoffeeInfo
import com.chs.your_body_profile.data.mapper.toDrinkInfoEntity
import com.chs.your_body_profile.data.mapper.toDrinkWaterInfo
import com.chs.your_body_profile.data.mapper.toFoodInfo
import com.chs.your_body_profile.data.mapper.toFoodInfoEntity
import com.chs.your_body_profile.data.mapper.toHemoglobinA1cInfo
import com.chs.your_body_profile.data.mapper.toHemoglobinA1cInfoEntity
import com.chs.your_body_profile.data.mapper.toInsulinInfo
import com.chs.your_body_profile.data.mapper.toInsulinInfoEntity
import com.chs.your_body_profile.data.mapper.toMedicineInfo
import com.chs.your_body_profile.data.mapper.toMedicineInfoEntity
import com.chs.your_body_profile.data.mapper.toWeightInfo
import com.chs.your_body_profile.data.mapper.toWeightInfoEntity
import com.chs.your_body_profile.data.source.db.dao.BloodPressureDao
import com.chs.your_body_profile.data.source.db.dao.BloodSugarDao
import com.chs.your_body_profile.data.source.db.dao.DrinkDao
import com.chs.your_body_profile.data.source.db.dao.FoodDao
import com.chs.your_body_profile.data.source.db.dao.HemoglobinA1cDao
import com.chs.your_body_profile.data.source.db.dao.InsulinDao
import com.chs.your_body_profile.data.source.db.dao.MedicineDao
import com.chs.your_body_profile.data.source.db.dao.WeightInfoDao
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.model.FoodInfo
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.domain.repository.BodyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class BodyRepositoryImpl @Inject constructor(
    private val bloodPressureDao: BloodPressureDao,
    private val bloodSugarDao: BloodSugarDao,
    private val drinkDao: DrinkDao,
    private val insulinDao: InsulinDao,
    private val hemoglobinA1cDao: HemoglobinA1cDao,
    private val medicineDao: MedicineDao,
    private val foodDao: FoodDao,
    private val weightInfoDao: WeightInfoDao
) : BodyRepository {

    override suspend fun <T> upsertInfo(info: T) {
        when (info) {
            is BloodPressureInfo -> {
               bloodPressureDao.upsert(info.toBloodPressureInfoEntity())
            }

            is BloodSugarInfo -> {
                bloodSugarDao.upsert(info.toBloodSugarInfoEntity())
            }

            is DrinkType -> {
                drinkDao.upsert(info.toDrinkInfoEntity(info))
            }

            is HemoglobinA1cInfo -> {
                hemoglobinA1cDao.upsert(info.toHemoglobinA1cInfoEntity())
            }

            is InsulinInfo -> {
                insulinDao.upsert(info.toInsulinInfoEntity())
            }

            is MedicineInfo -> {
                medicineDao.upsert(info.toMedicineInfoEntity())
            }

            is WeightInfo -> {
                weightInfoDao.upsert(info.toWeightInfoEntity())
            }
        }
    }

    override suspend fun <T> deleteInfo(info: T) {
        when (info) {
            is BloodPressureInfo -> {
                bloodPressureDao.delete(info.toBloodPressureInfoEntity())
            }

            is BloodSugarInfo -> {
                bloodSugarDao.delete(info.toBloodSugarInfoEntity())
            }

            is DrinkType -> {
                drinkDao.delete(info.toDrinkInfoEntity(info))
            }

            is HemoglobinA1cInfo -> {
                hemoglobinA1cDao.delete(info.toHemoglobinA1cInfoEntity())
            }

            is InsulinInfo -> {
                insulinDao.delete(info.toInsulinInfoEntity())
            }

            is MedicineInfo -> {
                medicineDao.delete(info.toMedicineInfoEntity())
            }

            is WeightInfo -> {
                weightInfoDao.delete(info.toWeightInfoEntity())
            }
        }
    }

    override fun getDayLastBloodPressureInfo(localDate: LocalDate): Flow<BloodPressureInfo?> {
        return bloodPressureDao.getDayLastInfo(localDate.toMillis()).map {
            it?.toBloodPressureInfo()
        }
    }

    override fun getDayLastBloodSugarInfo(localDate: LocalDate): Flow<BloodSugarInfo?> {
        return bloodSugarDao.getDayLastInfo(localDate.toMillis()).map {
            it?.toBloodSugarInfo()
        }
    }

    override fun getDayLastDrinkWaterInfo(localDate: LocalDate): Flow<DrinkWaterInfo?> {
        return drinkDao.getDayLastDrinkInfo(
            Constants.DRINK_TYPE_WATER,
            localDate.toMillis()
        ).map {
            it?.toDrinkWaterInfo()
        }
    }

    override fun getDayLastDrinkCoffeeInfo(localDate: LocalDate): Flow<DrinkCoffeeInfo?> {
        return drinkDao.getDayLastDrinkInfo(
            Constants.DRINK_TYPE_COFFEE,
            localDate.toMillis()
        ).map {
            it?.toDrinkCoffeeInfo()
        }
    }

    override fun getDayLastHemoglobinA1cInfo(localDate: LocalDate): Flow<HemoglobinA1cInfo?> {
        return hemoglobinA1cDao.getDayLastInfo(localDate.toMillis()).map {
            it?.toHemoglobinA1cInfo()
        }
    }

    override fun getDayLastInsulinInfo(localDate: LocalDate): Flow<InsulinInfo?> {
        return insulinDao.getDayLastInfo(localDate.toMillis()).map {
            it?.toInsulinInfo()
        }
    }

    override fun getDayLastMedicineInfo(localDate: LocalDate): Flow<MedicineInfo?> {
        return medicineDao.getDayLastInfo(localDate.toMillis()).map {
            it?.toMedicineInfo()
        }
    }

    override fun getDayLastFoodInfo(localDate: LocalDate): Flow<FoodInfo?> {
        return foodDao.getDayLastFoodInfo(localDate.toMillis()).map {
            it?.toFoodInfo()
        }
    }

    override fun getDayTotalCalorie(localDate: LocalDate): Flow<Int> {
        return foodDao.getDayTotalCalorie(localDate.toMillis())
    }

    override fun getDayLastWeightInfo(localDate: LocalDate): Flow<WeightInfo?> {
        return weightInfoDao.getDayLastInfo(localDate.toMillis()).map {
            it?.toWeightInfo()
        }
    }

    private fun getLastDayBloodPressureInfo(localDate: LocalDate): Flow<BloodPressureInfo?> {
        return bloodPressureDao.getDayLastInfo(localDate.toMillis()).map {
            it?.toBloodPressureInfo()
        }
    }
}