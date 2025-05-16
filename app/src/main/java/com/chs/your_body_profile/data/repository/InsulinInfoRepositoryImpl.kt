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
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class InsulinInfoRepositoryImpl @Inject constructor(
    private val insulinDao: InsulinDao
) : InsulinRepository {

    override fun getDayLastInfo(time: LocalDateTime): Flow<InsulinInfo?> {
        return insulinDao.getDayLastInfo(time.toMillis()).map { it?.toInsulinInfo() }
    }

    override fun getDayPagingInfo(): Flow<PagingData<Pair<LocalDate, List<InsulinInfo>>>> {
        return Pager(
            PagingConfig(pageSize = 5)
        ) {
            DayInsulinInfoPaging(insulinDao)
        }.flow
    }

    override suspend fun deleteInfo(info: InsulinInfo) {
        insulinDao.delete(info.toInsulinInfoEntity())
    }

    override suspend fun upsertInfo(info: InsulinInfo) {
        insulinDao.upsert(info.toInsulinInfoEntity())
    }
}