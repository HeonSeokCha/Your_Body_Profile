package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.WeightInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface WeightRepository : BaseInfoRepository<WeightInfo> {

    fun getDayPagingInfo(): Flow<PagingData<Pair<LocalDate, List<WeightInfo>>>>
}