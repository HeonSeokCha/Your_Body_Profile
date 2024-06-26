package com.chs.your_body_profile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toBloodSugarInfo
import com.chs.your_body_profile.data.mapper.toBloodSugarInfoEntity
import com.chs.your_body_profile.data.source.db.dao.BloodSugarDao
import com.chs.your_body_profile.data.source.paging.DayBloodSugarPaging
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.repository.BloodSugarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class BloodSugarRepositoryImpl @Inject constructor(
    private val bloodSugarDao: BloodSugarDao
): BloodSugarRepository {
    override fun getDayMinInfo(localDate: LocalDate): Flow<Int> {
        return bloodSugarDao.getDayMinInfo(localDate.toMillis())
    }

    override fun getDayMaxInfo(localDate: LocalDate): Flow<Int> {
        return bloodSugarDao.getDayMaxInfo(localDate.toMillis())
    }

    override fun getDayAvgInfo(localDate: LocalDate): Flow<Int> {
        return bloodSugarDao.getDayAvgInfo(localDate.toMillis())
    }

    override suspend fun upsertInfo(info: BloodSugarInfo) {
        bloodSugarDao.upsert(info.toBloodSugarInfoEntity())
    }

    override suspend fun deleteInfo(info: BloodSugarInfo) {
        bloodSugarDao.delete(info.toBloodSugarInfoEntity())
    }

    override fun getDayLastInfo(time: LocalDateTime): Flow<BloodSugarInfo?> {
        return bloodSugarDao.getDayLastInfo(time.toMillis()).map { it?.toBloodSugarInfo() }
    }

    override suspend fun getDayInfoList(localDate: LocalDate): List<BloodSugarInfo> {
        return bloodSugarDao.getDayInfoList(localDate.toMillis()).map {
            it.toBloodSugarInfo()
        }
    }

    override fun getDayPagingInfo(): Flow<PagingData<Pair<LocalDate, Int>>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            DayBloodSugarPaging(bloodSugarDao)
        }.flow
    }
}