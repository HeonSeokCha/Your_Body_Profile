package com.chs.your_body_profile.domain.repository

import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface HemoglobinA1cRepository : BaseRepository<HemoglobinA1cInfo> {

    fun getDayInfo(localDate: LocalDate): Flow<HemoglobinA1cInfo?>
}