package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface BloodSugarRepository : BaseInfoRepository<BloodSugarInfo> {

    fun getDayMinInfo(localDate: LocalDate): Flow<Int>

    fun getDayMaxInfo(localDate: LocalDate): Flow<Int>

    fun getDayAvgInfo(localDate: LocalDate): Flow<Int>

    fun getDayPagingInfo(localDate: LocalDate): Flow<PagingData<Pair<LocalDate, List<BloodPressureInfo>>>>
}