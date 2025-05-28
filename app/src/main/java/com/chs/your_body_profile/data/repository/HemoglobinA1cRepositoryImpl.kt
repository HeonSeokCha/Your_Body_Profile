package com.chs.your_body_profile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toHemoglobinA1cInfo
import com.chs.your_body_profile.data.mapper.toHemoglobinA1cInfoEntity
import com.chs.your_body_profile.data.source.db.dao.HemoglobinA1cDao
import com.chs.your_body_profile.data.source.paging.DayHemoglobinA1cInfoPaging
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.repository.BaseInfoRepository
import com.chs.your_body_profile.domain.repository.HemoglobinA1cRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class HemoglobinA1cRepositoryImpl @Inject constructor(
    private val hemoglobinA1cDao: HemoglobinA1cDao
) : HemoglobinA1cRepository {

    override fun getDayInfo(): Flow<HemoglobinA1cInfo?> {
        return hemoglobinA1cDao.getDayLastInfo().map {
            it?.toHemoglobinA1cInfo()
        }
    }

    override fun getDayPagingInfo(): Flow<PagingData<Pair<LocalDate, List<HemoglobinA1cInfo>>>> {
        return Pager(
            PagingConfig(pageSize = 5)
        ) {
            DayHemoglobinA1cInfoPaging(hemoglobinA1cDao)
        }.flow
    }

    override suspend fun deleteInfo(info: HemoglobinA1cInfo) {
        hemoglobinA1cDao.delete(info.toHemoglobinA1cInfoEntity())
    }

    override suspend fun upsertInfo(info: HemoglobinA1cInfo) {
        hemoglobinA1cDao.upsert(info.toHemoglobinA1cInfoEntity())
    }
}