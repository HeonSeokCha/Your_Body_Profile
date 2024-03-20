package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

interface InsulinRepository : BaseRepository<InsulinInfo> {

    fun getDayLastInfo(time: LocalDateTime): Flow<InsulinInfo?>

    fun getDayPagingInfo(): Flow<PagingData<Pair<LocalDate, Int>>>
}