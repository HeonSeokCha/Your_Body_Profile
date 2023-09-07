package com.chs.your_body_profile.data.repository

import com.chs.your_body_profile.data.source.db.dao.InsulinDao
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.repository.BaseInfoRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class InsulinInfoRepositoryImpl @Inject constructor(
    private val insulinDao: InsulinDao
) : BaseInfoRepository<InsulinInfo> {
    override fun getDayLastInfo(localDate: LocalDate): Flow<InsulinInfo?> {
        TODO("Not yet implemented")
    }

    override fun getDayInfoList(localDate: LocalDate): Flow<List<InsulinInfo>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteInfo(info: InsulinInfo) {
        TODO("Not yet implemented")
    }

    override suspend fun upsertInfo(info: InsulinInfo) {
        TODO("Not yet implemented")
    }
}