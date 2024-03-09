package com.chs.your_body_profile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toBloodPressureInfo
import com.chs.your_body_profile.data.mapper.toBloodPressureInfoEntity
import com.chs.your_body_profile.data.source.db.dao.BloodPressureDao
import com.chs.your_body_profile.data.source.paging.DayBloodPressurePaging
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

    override suspend fun getDayPagingInfoList(): Flow<PagingData<Pair<LocalDate, List<BloodPressureInfo>>>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            DayBloodPressurePaging(bloodPressureDao)
        }.flow
    }

    override suspend fun getDayLastInfo(localDate: LocalDate): BloodPressureInfo? {
        return bloodPressureDao.getDayLastInfo(localDate.toMillis())?.toBloodPressureInfo()
    }

    override suspend fun getDayInfoList(localDate: LocalDate): List<BloodPressureInfo> {
        return bloodPressureDao.getDayInfoList(localDate.toMillis()).map {
            it.toBloodPressureInfo()
        }
    }
}