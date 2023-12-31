package com.chs.your_body_profile.data.repository

import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toBloodPressureInfo
import com.chs.your_body_profile.data.mapper.toBloodPressureInfoEntity
import com.chs.your_body_profile.data.source.db.dao.BloodPressureDao
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.repository.BloodPressureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class BloodPressureRepositoryImpl @Inject constructor(
    private val bloodPressureDao: BloodPressureDao
) : BloodPressureRepository {

    override fun getDayMinInfo(localDate: LocalDate): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun getDayMaxInfo(localDate: LocalDate): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun getDayAvgInfo(localDate: LocalDate): Flow<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun upsertInfo(info: BloodPressureInfo) {
        bloodPressureDao.upsert(info.toBloodPressureInfoEntity())
    }

    override suspend fun deleteInfo(info: BloodPressureInfo) {
        bloodPressureDao.delete(info.toBloodPressureInfoEntity())
    }

    override fun getDayLastInfo(localDate: LocalDate): Flow<BloodPressureInfo?> {
        return bloodPressureDao.getDayLastInfo(localDate.toMillis()).map {
            it?.toBloodPressureInfo()
        }
    }

    override fun getDayInfoList(localDate: LocalDate): Flow<List<BloodPressureInfo>> {
        return bloodPressureDao.getDayInfoList(localDate.toMillis()).map {
            it.map { bloodPressureInfoEntity ->
                bloodPressureInfoEntity.toBloodPressureInfo()
            }
        }
    }
}