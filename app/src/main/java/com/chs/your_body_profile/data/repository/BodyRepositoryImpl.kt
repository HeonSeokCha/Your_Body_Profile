package com.chs.your_body_profile.data.repository

import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toBloodPressureInfo
import com.chs.your_body_profile.data.mapper.toBloodPressureInfoEntity
import com.chs.your_body_profile.data.mapper.toBloodSugarInfoEntity
import com.chs.your_body_profile.data.mapper.toBodySummaryInfoEntity
import com.chs.your_body_profile.data.mapper.toDrinkInfoEntity
import com.chs.your_body_profile.data.mapper.toHemoglobinA1cInfoEntity
import com.chs.your_body_profile.data.mapper.toInsulinInfoEntity
import com.chs.your_body_profile.data.mapper.toMedicineInfoEntity
import com.chs.your_body_profile.data.mapper.toWeightInfoEntity
import com.chs.your_body_profile.data.source.db.dao.BloodPressureDao
import com.chs.your_body_profile.data.source.db.dao.BloodSugarDao
import com.chs.your_body_profile.data.source.db.dao.BodySummaryDao
import com.chs.your_body_profile.data.source.db.dao.DrinkDao
import com.chs.your_body_profile.data.source.db.dao.HemoglobinA1cDao
import com.chs.your_body_profile.data.source.db.dao.InsulinDao
import com.chs.your_body_profile.data.source.db.dao.MedicineDao
import com.chs.your_body_profile.data.source.db.dao.WeightInfoDao
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.BodySummaryInfo
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.domain.repository.BodyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class BodyRepositoryImpl @Inject constructor(
    private val bodySummaryDao: BodySummaryDao,
    private val bloodPressureDao: BloodPressureDao,
    private val bloodSugarDao: BloodSugarDao,
    private val drinkDao: DrinkDao,
    private val insulinDao: InsulinDao,
    private val hemoglobinA1cDao: HemoglobinA1cDao,
    private val medicineDao: MedicineDao,
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

            is BodySummaryInfo -> {
                bodySummaryDao.upsert(info.toBodySummaryInfoEntity())
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

            is BodySummaryInfo -> {
                bodySummaryDao.delete(info.toBodySummaryInfoEntity())
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

    override suspend fun insertInitToDayBodySummaryInfo() {
        TODO("Not yet implemented")
    }

    private fun getLastDayBloodPressureInfo(localDate: LocalDate): Flow<BloodPressureInfo?> {
        return bloodPressureDao.getDayLastInfo(localDate.toMillis()).map {
            it?.toBloodPressureInfo()
        }
    }
}