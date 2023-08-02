package com.chs.your_body_profile.data.repository

import com.chs.your_body_profile.data.mapper.toBodyInfo
import com.chs.your_body_profile.data.mapper.toBodyInfoEntity
import com.chs.your_body_profile.data.source.db.dao.*
import com.chs.your_body_profile.domain.model.BodyMeasureInfo
import com.chs.your_body_profile.domain.repository.BodyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BodyRepositoryImpl @Inject constructor(
     private val bodyMeasureInfoDao: BodyMeasureInfoDao,
     private val bloodPressureDao: BloodPressureDao,
     private val bloodSugarDao: BloodSugarDao,
     private val drinkDao: DrinkDao,
     private val insulinDao: InsulinDao,
     private val hemoglobinA1cDao: HemoglobinA1cDao,
     private val medicineDao: MedicineDao
) : BodyRepository {

    override fun getBodyMeasureList(): Flow<List<BodyMeasureInfo>> {
        return bodyMeasureInfoDao.getSortedBodyProfileList().map { infoEntityList ->
            infoEntityList.map {
                it.toBodyInfo()
            }
        }
    }

    override suspend fun updateBodyMeasureInfo(bodyMeasureInfo: BodyMeasureInfo) {
        bodyMeasureInfoDao.update(bodyMeasureInfo.toBodyInfoEntity())
    }
}