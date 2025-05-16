package com.chs.your_body_profile.domain.repository

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

interface BaseInfoRepository<T> : BaseRepository<T> {

    fun getDayLastInfo(time: LocalDateTime): Flow<T?>

}