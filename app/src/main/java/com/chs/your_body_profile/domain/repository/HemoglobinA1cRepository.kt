package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

interface HemoglobinA1cRepository : BaseInfoRepository<HemoglobinA1cInfo> {

    fun getDayPagingInfo(): Flow<PagingData<Pair<LocalDate, List<HemoglobinA1cInfo>>>>
}