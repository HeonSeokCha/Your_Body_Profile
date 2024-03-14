package com.chs.your_body_profile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toWeightInfo
import com.chs.your_body_profile.data.mapper.toWeightInfoEntity
import com.chs.your_body_profile.data.source.db.dao.WeightInfoDao
import com.chs.your_body_profile.data.source.paging.DayWeightPaging
import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.domain.repository.WeightRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class WeightRepositoryImpl @Inject constructor(
    private val weightInfoDao: WeightInfoDao
) : WeightRepository {

    override suspend fun getDayLastInfo(localDate: LocalDate): WeightInfo? {
        return weightInfoDao.getDayLastInfo(localDate.toMillis())?.toWeightInfo()
    }

    override suspend fun getDayInfoList(localDate: LocalDate): List<WeightInfo> {
        return weightInfoDao.getDayInfoList(localDate.toMillis()).map {
           it.toWeightInfo()
        }
    }

    override suspend fun upsertInfo(info: WeightInfo) {
        weightInfoDao.upsert(info.toWeightInfoEntity())
    }

    override suspend fun deleteInfo(info: WeightInfo) {
        weightInfoDao.delete(info.toWeightInfoEntity())
    }

    override fun getDayPagingInfo(): Flow<PagingData<Pair<LocalDate, List<Int>>>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            DayWeightPaging(weightInfoDao)
        }.flow
    }
}