package com.chs.your_body_profile.domain.repository

import com.chs.your_body_profile.domain.model.InsulinInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface InsulinRepository : BaseRepository<InsulinInfo> {

    fun getDayInfo(localDate: LocalDate): Flow<InsulinInfo?>
}