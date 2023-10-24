package com.chs.your_body_profile.data.repository

import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toInsulinInfo
import com.chs.your_body_profile.data.mapper.toInsulinInfoEntity
import com.chs.your_body_profile.data.source.db.dao.InsulinDao
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.repository.BaseInfoRepository
import com.chs.your_body_profile.domain.repository.InsulinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class InsulinInfoRepositoryImpl @Inject constructor(
    private val insulinDao: InsulinDao
) : InsulinRepository {

    override fun getDayInfo(localDate: LocalDate): Flow<InsulinInfo?> {
        return insulinDao.getDayInfo(localDate.toMillis()).map {
            it?.toInsulinInfo()
        }
    }

    override suspend fun deleteInfo(info: InsulinInfo) {
        insulinDao.delete(info.toInsulinInfoEntity())
    }

    override suspend fun upsertInfo(info: InsulinInfo) {
        insulinDao.upsert(info.toInsulinInfoEntity())
    }
}