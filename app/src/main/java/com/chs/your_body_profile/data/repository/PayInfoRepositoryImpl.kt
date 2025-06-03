package com.chs.your_body_profile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chs.your_body_profile.data.mapper.toPayInfoEntity
import com.chs.your_body_profile.data.mapper.toPaymentInfo
import com.chs.your_body_profile.data.source.db.dao.PayInfoDao
import com.chs.your_body_profile.data.source.paging.DayPayInfoPaging
import com.chs.your_body_profile.domain.model.PaymentInfo
import com.chs.your_body_profile.domain.repository.PayInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class PayInfoRepositoryImpl @Inject constructor(
    private val payInfoDao: PayInfoDao
): PayInfoRepository {
    override fun getDayPagingInfo(): Flow<PagingData<Pair<LocalDate, List<PaymentInfo>>>> {
        return Pager(PagingConfig(pageSize = 5)) {
            DayPayInfoPaging(payInfoDao)
        }.flow
    }

    override fun getDayLastInfo(): Flow<PaymentInfo?> {
        return payInfoDao.getDayLastInfo().map { it?.toPaymentInfo() }
    }

    override suspend fun upsertInfo(info: PaymentInfo) {
        payInfoDao.upsert(info.toPayInfoEntity())
    }

    override suspend fun deleteInfo(info: PaymentInfo) {
        payInfoDao.delete(info.toPayInfoEntity())
    }
}