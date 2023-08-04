package com.chs.your_body_profile.data.repository

import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toBloodPressureInfo
import com.chs.your_body_profile.data.source.db.dao.*
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.BodySummaryInfo
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.domain.repository.BodyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime
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
    override suspend fun insertInitToDayBodySummaryInfo() {
        TODO("Not yet implemented")
    }

    override fun <T> getLastDayInfo(localDate: LocalDate): Flow<T?> {
        TODO("Not yet implemented")
    }

    override fun <T> getLastDayInfoList(localDate: LocalDate): Flow<List<T>> {
        TODO("Not yet implemented")
    }

    override suspend fun <T> upsertInfo(info: T) {
        TODO("Not yet implemented")
    }

    override suspend fun <T> deleteInfo(info: T) {
        TODO("Not yet implemented")
    }

    private fun getLastDayBloodPressureInfo(localDate: LocalDate): Flow<BloodPressureInfo?> {
        return bloodPressureDao.getDayLastInfo(localDate.toMillis()).map {
            it?.toBloodPressureInfo()
        }
    }

}