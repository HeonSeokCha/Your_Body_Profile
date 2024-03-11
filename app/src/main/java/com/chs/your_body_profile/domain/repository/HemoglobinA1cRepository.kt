package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface HemoglobinA1cRepository : BaseRepository<HemoglobinA1cInfo> {

    suspend fun getDayInfo(localDate: LocalDate): HemoglobinA1cInfo?

    fun getDayPagingList(): Flow<PagingData<Pair<LocalDate, List<HemoglobinA1cInfo>>>>
}