package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.PaymentInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface PayInfoRepository : BaseInfoRepository<PaymentInfo> {

    fun getDayPagingInfo(): Flow<PagingData<Pair<LocalDate, List<PaymentInfo>>>>
}