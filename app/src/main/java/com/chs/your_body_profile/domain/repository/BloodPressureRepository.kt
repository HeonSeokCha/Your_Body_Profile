package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface BloodPressureRepository : BaseInfoRepository<BloodPressureInfo> {
    fun getDayPagingInfo(): Flow<PagingData<Pair<LocalDate, List<BloodPressureInfo>>>>
}