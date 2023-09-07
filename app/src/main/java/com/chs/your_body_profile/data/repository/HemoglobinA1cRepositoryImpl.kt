package com.chs.your_body_profile.data.repository

import com.chs.your_body_profile.data.source.db.dao.HemoglobinA1cDao
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.repository.BaseInfoRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class HemoglobinA1cRepositoryImpl @Inject constructor(
    private val hemoglobinA1cDao: HemoglobinA1cDao
) : BaseInfoRepository<HemoglobinA1cInfo> {
    override fun getDayLastInfo(localDate: LocalDate): Flow<HemoglobinA1cInfo?> {
        TODO("Not yet implemented")
    }

    override fun getDayInfoList(localDate: LocalDate): Flow<List<HemoglobinA1cInfo>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteInfo(info: HemoglobinA1cInfo) {
        TODO("Not yet implemented")
    }

    override suspend fun upsertInfo(info: HemoglobinA1cInfo) {
        TODO("Not yet implemented")
    }

}