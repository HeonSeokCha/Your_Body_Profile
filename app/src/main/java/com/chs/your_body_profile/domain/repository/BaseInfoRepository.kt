package com.chs.your_body_profile.domain.repository

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface BaseInfoRepository<T> : BaseRepository<T> {

    fun getDayLastInfo(localDate: LocalDate): Flow<T?>

    fun getDayInfoList(localDate: LocalDate): Flow<List<T>>
}