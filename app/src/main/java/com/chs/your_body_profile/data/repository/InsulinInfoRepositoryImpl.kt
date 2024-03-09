package com.chs.your_body_profile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toInsulinInfo
import com.chs.your_body_profile.data.mapper.toInsulinInfoEntity
import com.chs.your_body_profile.data.source.db.dao.InsulinDao
import com.chs.your_body_profile.data.source.paging.DayInsulinInfoPaging
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.repository.InsulinRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class InsulinInfoRepositoryImpl @Inject constructor(
    private val insulinDao: InsulinDao
) : InsulinRepository {

    override suspend fun getDayInfo(localDate: LocalDate): InsulinInfo? {
        return insulinDao.getDayInfo(localDate.toMillis())?.toInsulinInfo()
    }

    override suspend fun deleteInfo(info: InsulinInfo) {
        insulinDao.delete(info.toInsulinInfoEntity())
    }

    override suspend fun getDayPagingInfoList(): Flow<PagingData<Pair<LocalDate, List<InsulinInfo>>>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            DayInsulinInfoPaging(insulinDao)
        }.flow
    }

    override suspend fun upsertInfo(info: InsulinInfo) {
        insulinDao.upsert(info.toInsulinInfoEntity())
    }
}