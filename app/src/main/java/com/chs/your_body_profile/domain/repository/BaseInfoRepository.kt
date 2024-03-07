package com.chs.your_body_profile.domain.repository

import java.time.LocalDate

interface BaseInfoRepository<T> : BaseRepository<T> {

    suspend fun getDayLastInfo(localDate: LocalDate): T?

    suspend fun getDayInfoList(localDate: LocalDate): List<T>
}