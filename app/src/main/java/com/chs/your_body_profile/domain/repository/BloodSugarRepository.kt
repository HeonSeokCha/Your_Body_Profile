package com.chs.your_body_profile.domain.repository

import com.chs.your_body_profile.domain.model.BloodSugarInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface BloodSugarRepository : BaseRepository<BloodSugarInfo> {

    fun getDayMinInfo(localDate: LocalDate): Flow<Int>

    fun getDayMaxInfo(localDate: LocalDate): Flow<Int>

    fun getDayAvgInfo(localDate: LocalDate): Flow<Int>
}